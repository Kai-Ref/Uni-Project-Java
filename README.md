# Uni-Project-Java
<div>This Java Project was a Course Requirement for a "Java- Programmieren kommerzieller Systeme"-Course Exam.
</div> 
<div>
It builds up a CovidTracker, where guests can put their names, arrival and leaving time, as well as their mobile number into a GUI. This can be repeated for several guests. When a listed person has Covid we can change a Tab in the GUI, where we input the name and mobile number to filter out which other person has been simultaneously present with the infected guest. However, this project is not using underlying data structures, so the application only works, when it's <i>"left open"</i>.
The <b>Java Source Code</b> lies in the <a href = "https://github.com/Kai-Ref/Uni-Project-Java/tree/main/Covid%20Tracker/src" target = "_self">src</a> Folder, while the bin Folder contains the .class files.
</div> 
<div>
The Project is build by using 4 different Java Sources Codes:
  <ul>
<li><a href = "https://github.com/Kai-Ref/Uni-Project-Java/blob/main/Covid%20Tracker/src/CovidTrackerMain.java" target = "_self">Main</a> ->contains the main-Method and starts the Project</li>
<li><a href = "https://github.com/Kai-Ref/Uni-Project-Java/blob/main/Covid%20Tracker/src/model/ModelKlasse.java" target = "_self">Model</a> ->implements the logic of our program, which is mainly accessed by the Controller</li>
<li><a href = "https://github.com/Kai-Ref/Uni-Project-Java/blob/main/Covid%20Tracker/src/view/ViewKlasse.java" target = "_self">View</a> -> depicts the Window for the CovidTracker and displays Errormessages, additionally implements the event handling for the Buttons</li>
<li><a href = "https://github.com/Kai-Ref/Uni-Project-Java/blob/main/Covid%20Tracker/src/controller/ControllerKlasse.java" target = "_self">Controller</a> ->This class implements the necessary Listeners which act upon defined events</li>
</div> 
<div>
The Source Code uses many german variables and the comments are also in German, as this course was held in German.
 </div>
