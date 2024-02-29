# jovine360_api_web

As a developer, repeating the same process, like creating a data access object DAO for every database for every new project can be tiresome. Imagine having a program that can implement jdbc DAO for every database irrespective of the difference in the schema. For example, a dao that works with a database table with 10 fields as well as with another table with 8 fields without having to recreate it.

 Introducing the following Java API bundled in jovine360_api_web api:
 1. AutoIdDAO: for generating unique identity codes. it can be used for primary key values
 2. CheckDAO: for checking and validating records in the database.
 3. CodeGen: can be used to generate refcodes for unique transactions.
 4. DataAccessObject: for all jdbc DAO operations. it also performs custom query execution.
 5. DataConnection: creates database connection (Note: this release works with MySQL)
 6. DateTime: it works with date and time operation, generate expiry dates, formats date and time (e.g. 2023-03-14 to 14th March|Mar, 2023), etc
 7. Ellipsis: for triming and adding ellipsis(...) to the end of a sentence or descriptions.
 8. Files: from creating to writing to deleting of a file and folder.
 9. Formatter: it formats numeric values in either int, Double or String type to a currency format and returns a String result.
 10. GeneratePassword: generates unique random passwords. Alphabets only, Alphanumeric and numeric only (for otps)
 11. ReportDataDAO: for fetching datas/single values from any database.

Note: For detail information on the package and their clasess and examples kindly refer to the javadoc in the target/sites folder.

