import java.sql.*;

import com.salesucation.sparkphp.PHPRenderer;

import io.github.rhildred.OpenShiftSQLiteSource;
import io.github.rhildred.ResultSetValue;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App
{
    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 4567;
    public static void main(String[] args) {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        externalStaticFileLocation(System.getProperty("user.dir") + "/public/");
        PHPRenderer php = new PHPRenderer();
        php.setViewDir("views/");
        final Connection connection = OpenShiftSQLiteSource.getConnection();
        try{
            get("/", (request, response) -> {
                return php.render("index.phtml");
            });
            get("/postings/:page", (request, response) -> {
                String rc = "";
                PreparedStatement oStmt = null;
                try{
                    String sSQL = "SELECT * FROM jobpostings WHERE id = ?";
                    oStmt = connection.prepareStatement(sSQL);
                    oStmt.setString(1, request.params(":page"));
                    ResultSet oRs = oStmt.executeQuery();
                    String sModel = ResultSetValue.toJsonString(oRs);
                    oRs.close();
                    rc = php.render("page.phtml", sModel);

                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    try{
                        if(oStmt != null) oStmt.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                return rc;

            });
            get("/postings", (request, response) -> {
                String rc = "";
                try{
                    Statement oStmt = connection.createStatement();
                    String sSQL = "SELECT * FROM jobpostings";
                    ResultSet oRs = oStmt.executeQuery(sSQL);
                    rc = ResultSetValue.toJsonString(oRs);
                    oRs.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                return rc;

            });
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}