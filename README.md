# tinyflix-automation
Selenium test suite for TinyFlix app
**Prerequisites**
Make sure the following tools are installed on your machine:
1.Java JDK (version 8 or higher, e.g., 21.0.6)
2.Maven (for dependency management)
3.Google Chrome Browser
4.Internet connection (required for Maven to download dependencies)
5.Eclipse IDE (optional, but recommended)
**Clone or Download the Repository**
Clone using Git (if set up): 
Or download the ZIP file and extract it locally.
**Import Project in Eclipse (Optional)**
1.Open Eclipse
2.Go to: File → Import
3.Select: Maven → Existing Maven Projects
4.Browse and select the project folder
5.Click Finish
**Run the Test**
Right-click the class file → Run As → Java Application
**Additional Notes**
1.Make sure the application under test (TinyFlix) is running on http://localhost:5173/
2.You may need to update the URL in your test classes based on the deployment
3.The project includes tests for:
i)Multiple bookmark functionality
ii)Error handling
iii)Video filter logic

