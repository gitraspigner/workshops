# Year Up United / Pluralsight - Java Workshops (Ravi's Solutions)
## Course Taught By: Maaike Van Putten

### 🛠️ Tools Used
![Java](https://img.shields.io/badge/language-Java-blue.svg) ![IDE](https://img.shields.io/badge/IDE-IntelliJ-orange) <br>
![SQL](https://img.shields.io/badge/language-SQL-blue.svg) / ![mySQL](https://img.shields.io/badge/database-MySQL-4479A1.svg?logo=mysql&logoColor=white) <br>
![Maven](https://img.shields.io/badge/build-Maven-C71A36.svg?logo=apachemaven&logoColor=white) <br>
![Windows CMD](https://img.shields.io/badge/shell-Windows%20CMD-white.svg?logo=windows-terminal&logoColor=0078D6)

| Active/Inactive | Active Dates |
| --- | --- |
| ![Status](https://img.shields.io/badge/status-active-brightgreen) | 10/01/2025 - XX/XX/XXXX|

### 📝 Description
Contains a collection of my weekly workshop solutions. <br>
The workbook (PDF) containing most of the workshop writeups from each week cannot be uploaded due to Pluralsight ownership rights. <br>
The instructor's versions of these workshop solutions can be found [here](https://github.com/BrightBoost/learningjava/tree/main/src/main/java/com/pluralsight) (given that the repo is still active). <br>
Most of the instructor's workshop solutions should be titled 'workshop' or include that in the folder or filename. <br>

#### 🕒 Commits History Here<br>
[Click Here](https://github.com/gitraspigner/workshops/commits/master) <br>

### 💭 My Thoughts: <br>
#### Week/Workshop 1
- **Workshop Title:** Financial Calculators (Language: Java, IDE: IntelliJ)
- **Program(s) Description:** Calculates and displays results for 3 different financial annuity calculators: mortgage, present value, and future value.
  - **Thoughts:** This workshop created a lot of discipline within me for properly understanding PEMDAS (the order of mathematical operations) via the usage/pairing of parenthesis in mathematical expressions & calculations in Java. This is primarily due to having to implement multiple mathematical formulas which are easily written on paper, but become more complex to write (and interpret) in Java code form.
---
#### Week/Workshop 2
- **Workshop Title:** Library (Language: Java, IDE: IntelliJ)
- **Program(s) Description:** Manages the inventory of books for a Library. Users use a menu to look up available books for checkout, checked out books to check in, or just to check in a book by its book ID.
  - **Thoughts:** It was helpful to review input handling and primitive array manipulation. It was a very short command line program with few options for user input, but was good practice for me, nonetheless.
---
#### Week/Workshop 3
- **Workshop Title:** Online Store (Language: Java, IDE: IntelliJ)
- **Program(s) Description:** Simulates an online store of products. Products and their information are taken from a file called 'products.csv'. User/shopper can add or remove products from their cart, and can search for products by the product name, product price, or department of the product they desire.
  - **Thoughts:** Similar to, but slightly more in depth than the Library workshop. This workshop was useful for learning the BufferedReader class in java for file reading (since I was used to using Scanner for file reading (and writing)). Due to the flexibility of the instructor and the writeup, I decided to use a HashMap to allow for a fast lookup of specific items (if retrieved via SKU). This didn't have a marginal impact on the execution time for the dataset provided (or even what I was able to generate), but I think it may have been good to implement or practice implementing. Even though it was not very hard for me to complete the implementation for this workshop, I am still proud of my work.
---
#### Week 5/6 - Workshop 4/5
- **Workshop Title:** Car Dealership (Language: Java, IDE: IntelliJ)
- **Program(s) Description:** Represents a car dealership including its info (name, address, and phone number) and its collection of vehicles (each including: vin number, year produced, make, model, type of vehicle (SUV, truck, etc...), color, odometer reading, and price) available in inventory.
  - **Thoughts:** Completing this workshop after Capstone 1 (Accounting Ledger [here](https://github.com/gitraspigner/capstones)) was fairly easy since concepts such as handling user input and file reading/writing had been required for that. I didn't run into many hiccups on this one. One thing I was able to implement which was new was a method within DealershipFileManager [here](https://github.com/gitraspigner/workshops/blob/master/src/com/pluralsight/Week5/DealershipFileManager.java) called errorMessage where I was able to cut down on the number of lines in my program by printing some (not all) error messages through it. I have now, not only started to also remove all of the blank lines from my java files (with the exeption of the blank EOF (end-of-file) line to adhere to POSIX standards) but also make my class javadoc comments as few lines as possible. I think I'll end up writing more for my class javadoc comments on the next capstone, so I'll have to challenge myself to keep those as short as possible (I learned this in college and I think it's a good standard). I also would like to include method javadoc comments, but haven't made the time to do so. For what it's worth, I still have (arguably) useful comments throughout my code to help any reader understand what I was thinking.
---
#### Week 7 - Workshop 6 (Currently omitted due to Capstone 2 work)
---
#### Week 8 - Workshop 7
- **Workshop Title:** Car Dealership Database (Languages: SQL/mySQL | IDEs: MySQL Workbench, Windows Command Prompt)
- **Program(s) Description:** Represents a car dealership's database including its info (name, address, and phone number) and its collection of vehicles (each including: vin number, year produced, make, model, type of vehicle (SUV, truck, etc...), color, odometer reading, and price) available in inventory.
  - **Thoughts:** This workshop was relatively simple for me since it was created from a fairly short writeup. The most useful part of this assignment was getting used to writing out SQL statements (syntax for different kinds of comments, where backticks (`) are used in statements, testing my program from the MySQL Workbench (and learning that interface a bit better, also getting faster at using it), and getting more familarity with using Git from the command line (Windows CMD) as opposed to using the more user-friendly VCS options within an IDE (like IntelliJ for the previous workshops). I was able to complete the basic elements of this program in a timely manner: Dealerships Table, Vehicles Table, Inventory Table, Sales Contracts Table, and tests for each). I typed everything by hand which helped a lot with knowing SQL syntax (where things go in different statements, what is/isn't required in each statement for it to execute, variable types and key declarations (primary, foreign, composite)) even though I've used this language before during my studies at The University of Washington. The only part I didn't write myself was the test data I included within my dealership (added in dealership.sql). I had ChatGPT help with that even though it was a fairly short section to complete and could have been done easily by me writing one insert statement, copy-pasting, and tweaking each time. Even so, I had to tweak that test data to accurately test for what I wanted to and for variety's sake because the data I got was a little too vertical for my liking. I also had to edit it to reflect that vehicles with contracts created for them were in fact, sold. However, all of the tests (tests.sql) were written by me. Regarding properly reflecting sold vehicles in the database, the same goes for the tests I wrote, I had to remove them from the inventory to accurately reflect that. Just a little extra work to make it sparkle the way I wanted it to. There was one part of this (declared as optional in the writeup) I wasn't able to implement (or haven't yet) due to time constraints which was another kind of contract: Lease Contracts. But what I ended up with included Sales Contracts, was working well, accurate according to specifications, included useful comments so someone who doesn't know SQL will know what my code does and what is expected to happen, and included tests (we love tests) and I would say I was pleased with what I had produced. Plus, I got very comfy with using (very basic) Windows CMD for Git (AND this comes a full week before we were even going to cover Command Line Interfaces and Git!!). I almost prefer it to using the user-friendly VCS option within IntelliJ for making changes. AND it was faster and more lightweight than the IDE. Great things all around (in my opinion).
---
#### Week 9 - Workshop 8
- **Workshop Title:** Car Dealership Database + Java Command Line Interface (Languages: SQL/mySQL, Java, Maven, JDBC | IDEs: IntelliJ, MySQL Workbench, Windows Command Prompt)
- **Program(s) Description:** Represents a car dealership's database including its info (name, address, and phone number), its collection of vehicles available in inventory (each including: vin number, whether it has been sold or not, year produced, make, model, type of vehicle (SUV, truck, etc...), color, odometer reading, and price), and a record of sales contracts for the vehicles have been sold (date of sale, vin of vehicle sold, dealership id vehicle was sold from, the customer's name the vehicle was sold to, the customer's email, and the price of the vehicle sold).
- **Thoughts:** This workshop wasn't that hard for me (other than the amount of time we had to complete the entire assignment, which was a few days). The part that took the most time for me was making each of the CRUD methods (which was more difficult without using Spring) for the Vehicle class. This one wasn't too difficult but I do think I could improve upon my speed so that my Javadoc class comments aren't left as the last item I have to complete for the workshop (aside from README documentation). In other words: a great review of syntax I've used before, and a humbling experience for accomplishing a great amount of work in a very short time. 

#### 🔖 Citation
I wrote this README.md, but I did indeed use ChatGPT to give my initial framework and to learn markdown formatting. Therefore here is an APA Style Citation for it:  <br>
OpenAI. (2025). ChatGPT (Oct 1 version) [Large language model]. https://chatgpt.com/ <br>

*I have to give credit where it's due, right?* <br>

**Last Edited: 12/10/2025**
