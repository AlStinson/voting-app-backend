CREATE TABLE "codes" (
  "id" uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  "value" varchar UNIQUE NOT NULL
);

CREATE TABLE "surveys" (
  "id" uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  "code" varchar UNIQUE NOT NULL,
  "name" varchar,
  "description" text,
  "active" boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE "survey_options" (
  "id" uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  "survey" uuid NOT NULL,
  "number" integer NOT NULL,
  "value" varchar NOT NULL,
  FOREIGN KEY ("survey") REFERENCES "surveys" ("id")
);

CREATE TABLE "survey_responses" (
  "id" uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  "survey" uuid NOT NULL,
  "option" uuid NOT NULL,
  "code" uuid NOT NULL,
  FOREIGN KEY ("survey") REFERENCES "surveys" ("id"),
  FOREIGN KEY ("option") REFERENCES "survey_options" ("id"),
  FOREIGN KEY ("code") REFERENCES "codes" ("id")
);