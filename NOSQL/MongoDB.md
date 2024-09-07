

## Glossary

- **Collections**: Similar to tables in SQL. Collections are used in NoSQL databases like MongoDB to group documents. 
- **Documents**: Each collection holds a set of documents, similar to how a table holds rows in SQL databases.

## Useful Commands

* **Show Databases**: `show dbs`
* **Use a Database**: `use <database_name>`
* **Show Collections**: `show collections`
* **Create a collection**: `db.createCollection("<collection_name>")`
* **Insert a document**: `db.<collection_name>.insertOne({ key: "value" })`
* **Find documents**: `db.<collection_name>.find()`
* **Update documents**: `db.<collection_name>.updateOne({ <query> }, { $set: { <field>: <value> } })`
* **Delete documents** : `db.mycollection.deleteOne({ name: "Alice" })`
* **Drop a collection**: `db.<collection_name>.drop()`


