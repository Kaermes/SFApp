SFApp

Needed/recommended: 

Maven (http://maven.apache.org/download.cgi) 
Netbeans (https://netbeans.org/downloads/)

Steps:
git clone the repository (git clone https://github.com/Kaermes/SFApp.git)
open the project in Netbeans 
  (File -> Open project -> navigate to project folder, should be recognized as a Maven project)
build with dependencies (rightclick project, select Build with Dependencies)
run the project

What does it do:
Fetches the data from cert.fi 
unzips it
reads all the lines from each file into a single list
goes through the list, analyzing the frequency of locations and categories
outputs the results into system.out (Run window in Netbeans)

Output will be in the specified format:
<category_name><space><n>\n
<location_name><space><n>\n

(there will be no other output, nor is there any space between the two different outputs.)
