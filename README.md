# Year Up United / Pluralsight - Java Workshops (Ravi's Solutions)
## Course Taught By: Maaike Van Putten

### 🛠️ Tools Used
![Java](https://img.shields.io/badge/language-Java-blue.svg)
![IDE](https://img.shields.io/badge/IDE-IntelliJ-orange)

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
- **Workshop Title:** Financial Calculators
- **Program(s) Description:** Calculates and displays results for 3 different financial annuity calculators: mortgage, present value, and future value.
  - **Thoughts:** This workshop created a lot of discipline within me for properly understanding PEMDAS (the order of mathematical operations) via the usage/pairing of parenthesis in mathematical expressions & calculations in Java. This is primarily due to having to implement multiple mathematical formulas which are easily written on paper, but become more complex to write (and interpret) in Java code form.
---
#### Week/Workshop 2
- **Workshop Title:** Library
- **Program(s) Description:** Manages the inventory of books for a Library. Users use a menu to look up available books for checkout, checked out books to check in, or just to check in a book by its book ID.
  - **Thoughts:** It was helpful to review input handling and primitive array manipulation. It was a very short command line program with few options for user input, but was good practice for me, nonetheless.
---
#### Week/Workshop 3
- **Workshop Title:** Online Store
- **Program(s) Description:** Simulates an online store of products. Products and their information are taken from a file called 'products.csv'. User/shopper can add or remove products from their cart, and can search for products by the product name, product price, or department of the product they desire.
  - **Thoughts:** Similar to, but slightly more in depth than the Library workshop. This workshop was useful for learning the BufferedReader class in java for file reading (since I was used to using Scanner for file reading (and writing)). Due to the flexibility of the instructor and the writeup, I decided to use a HashMap to allow for a fast lookup of specific items (if retrieved via SKU). This didn't have a marginal impact on the execution time for the dataset provided (or even what I was able to generate), but I think it may have been good to implement or practice implementing. Even though it was not very hard for me to complete the implementation for this workshop, I am still proud of my work.
---
#### Week5 - Workshop 4
-In Progress-
- **Workshop Title:** Car Dealership
- **Program(s) Description:** Represents a car dealership including its info (name, address, and phone number) and its collection of vehicles (each including: vin number, year produced, make, model, type of vehicle (SUV, truck, etc...), color, odometer reading, and price) available in inventory.
  - **Thoughts:** Completing this workshop after Capstone 1 (Accounting Ledger [here](https://github.com/gitraspigner/capstones)) was fairly easy since concepts such as handling user input and file reading/writing had been required for that. I didn't run into many hiccups on this one. One thing I was able to implement which was new was a method within DealershipFileManager [here](https://github.com/gitraspigner/workshops/blob/master/src/com/pluralsight/Week5/DealershipFileManager.java) called errorMessage where I was able to cut down on the number of lines in my program by printing some (not all) error messages through it. I have now, not only started to also remove all of the blank lines from my java files (with the exeption of the blank EOF (end-of-file) line to adhere to POSIX standards) but also make my class javadoc comments as few lines as possible. I think I'll end up writing more for my class javadoc comments on the next capstone, so I'll have to challenge myself to keep those as short as possible (I learned this in college and I think it's a good standard). I also would like to include method javadoc comments, but haven't made the time to do so. For what it's worth, I still have (arguably) useful comments throughout my code to help any reader understand what I was thinking.  

#### 🔖 Citation
I wrote this README.md, but I did indeed use ChatGPT to give my initial framework and to learn markdown formatting. Therefore here is an APA Style Citation for it:  <br>
OpenAI. (2025). ChatGPT (Oct 1 version) [Large language model]. https://chatgpt.com/ <br>

*I have to give credit where it's due, right?* <br>

**Last Edited: 10/25/2025**
