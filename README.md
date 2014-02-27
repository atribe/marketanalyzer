marketanalyzer
==============

MarketPredictor redone so I could figure out Spring and probably use Hibernate.

Usage Instructions
1. Download the source via git
2. Import as general project into eclipse
3. Right click on imported project and select Configure->Convert to Maven Project. See http://download.eclipsesource.com/~hstaudacher/m2e-tabris/convert-project.png
4. Added Maven Dependencies to the Deployment Assembly
  a. Right click project
  b. Select Properties
  c. Click on Deployment Assembly in the dialog box that pops up
  d. Click on Add and choose Java Build Path Entries, then hit next
  e. Click on Maven Dependencies and click finish.

Thats it. Now you can run the project on a tomcat server you've setup for Eclipse use.
