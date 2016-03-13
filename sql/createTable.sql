CREATE  TABLE "main"."jobpostings" ("id" VARCHAR PRIMARY KEY  NOT NULL , "title" VARCHAR NOT NULL, "datePosted" DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP, "educationRequirements" VARCHAR, "employmentType" VARCHAR NOT NULL  DEFAULT 'internship', "baseSalarySignificand" INTEGER NOT NULL  DEFAULT 0, "baseSalaryExponent" INTEGER NOT NULL DEFAULT 0, "salaryCurrency" VARCHAR DEFAULT 'CAD', "experienceRequirements" VARCHAR, "hiringOrganizationName" VARCHAR, "hiringOrganizationAddress" VARCHAR, "hiringOrganizationEmail" VARCHAR, "hiringOrganizationEmployeeName" VARCHAR, "incentiveCompensation" VARCHAR, "industry" VARCHAR, "jobLocationAddress" VARCHAR, "jobLocationLatitude" FLOAT, "jobLocationLongitude" FLOAT, "occupationalCategory" VARCHAR, "qualifications" VARCHAR, "responsibilities" VARCHAR, "skills" VARCHAR, "workHours" VARCHAR)