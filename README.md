# common-csv-reader
Given project is a demo project to load once all the resouces csv files and save them in cache memory.

Below dependency is used for reading csv files and converting them into Map:
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-csv</artifactId>
			<version>1.10.0</version>
		</dependency>

Cache is used to maintain the csv data in json format:

3 Endpoints for provide results as :

http://localhost:8080/loadAllCSVData

http://localhost:8080/getAllCSVData

http://localhost:8080/clearAllCSVData
