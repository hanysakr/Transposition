Main branch requries JAVA 21
----------
gradle build

java -jar transposition-0.0.1-SNAPSHOT.jar in/a.json 5 out/b.json


Java 17 branch 
-------------
gradle build

java -jar transposition-0.0.1-SNAPSHOT.jar in/a.json 5 out/b.json


Assumptions
------------
- There is no specified maximum sentiment in the assessment.
- If the transpose reaches the maximum/minimum boundaries, it will remain at those maximum or lower boundaries.
