# Merganser
The duck game!

## Importing the project into Eclipse for the first time
1. Clone from GitHub (using your prefered method)
2. Install Gradle to Eclipse 
    -> Help -> Install new software... -> Add... 
        (Name: gradle, Location: http://dist.springsource.com/release/TOOLS/gradle)
  2. Select 'Extensions / Gradle Integration'
  3. Press Finish
3. Import project to Eclipse as a Gradle Project
  1. File -> Import -> Gradle -> Gradle Project -> Browse for Merganser folder (wherever you cloned the git repository to) -> Build Model (at the top).
  2. Select all (You should see 3 folders) -> Finish.
4. You have finished importing the project into Eclipse

## Addding dependencies to the project
1. Find package on http://search.maven.org/
2. Open the 'merganser/build.gradle' file
3. Add ```compile "<group>:<name>:<version>"``` within ```dependencies {...}``` for the relevant project (core/desktop) e.g. for JUnit testing add 'testCompile 'junit:junit:4.12'' under the project(":core") section
See 'https://docs.gradle.org/current/userguide/artifact_dependencies_tutorial.html' for further instructions

## To get changes from GitHub
1. Exit Eclipse.
2. Run ```git pull``` or Pull from your git GUI
3. Re-open Eclipse (changes should have updated)