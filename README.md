Steps to run:

1. Unzip the archive.
2. Navigate to the unzipped directory using the command line. This should contain src and pom.xml.

  e.g:	cd /home/development/maze

3. Run maven build.

  e.g:	mvn clean package

4. Run the program from the executable jar in the target directory, providing the filepath containing the maze to solve. There are some samples in /src/main/resources included in the repo.

  e.g:	java -jar target/maze-1.0-jar-with-dependencies.jar /home/development/medium_input.txt





