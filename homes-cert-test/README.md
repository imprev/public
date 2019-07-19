#Homes API Cert Test

To run as a maven project do the following:

note: replace the bracketed [REDACTED_HOMES_TOKEN] with your actual token in quotes
mvn compile exec:java -Dexec.mainClass="com.imprev.homes.Main" -Dexec.args="[REDACTED_HOMES_TOKEN]"
 
ex.
mvn compile exec:java -Dexec.mainClass="com.imprev.homes.Main" -Dexec.args="aFakeTokenIsHereklfjdlks/sfdksld"
 

