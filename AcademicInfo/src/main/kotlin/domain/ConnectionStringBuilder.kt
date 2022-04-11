package domain

import java.io.File
import java.io.FileInputStream
import java.util.Properties


class ConnectionStringBuilder
{
    companion object {
        // Creates a connection string for postgresql
        // Needs a file which contains
        // 1. The host ip
        // 2. The port
        // 3. The database name
        fun createString() : String {
//            val path = System.getProperty("user.dir") + "/AcademicInfo/" + fileName
//            val data = File(path).readLines()
            val props = Properties()
            props.load(FileInputStream("gradle.properties"))
            val host = props.getProperty("database.host")
            val port = props.getProperty("database.port")
            val name = props.getProperty("database.name")

            return "jdbc:postgresql://${host}:${port}/${name}"
        }
    }
}