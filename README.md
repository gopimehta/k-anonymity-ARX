# Implementing k-anonymity using ARX library

## Table of contents:

1. Motivation
1. How to run the code?
1. Challenges and processes followed
1. Use cases and benefits
1. Future prospects
1. References

### Motivation:

People often think that the major reason behind companies investing in security is due to a large number of data breaches and fraud but unfortunately that’s not the case, it’s the regulatory laws that force organizations to invest in security measures. Organizations often need to exchange information with one another for various purposes which make the data vulnerable and puts the privacy of users at risk. Hence the need to make data unidentifiable while retaining the characteristics of data emerged. <br/> ARX provides various methods for implementing data anonymization and in this project, we shall explore k-anonymity using the ARX library to solve our very basic issue surrounding data.

### How to run the code?

- Download the code or clone from this repository.
- Eclipse IDE
  - Extract the zip file and open the project (I used ‘Eclipse IDE for java developers’ as it’s also recommended in official ARX documentation).
  - Within the `src` directory, find `org\deidentifier\arx\test` package and run the `AdultDataAnonymization.java` file.
- Command Line
  - Go to the application root directory and run the following commands sequentially
    1. `javac -cp library/libarx-3.9.0.jar src/org/deidentifier/arx/test/BaseClass.java -d bin/`
    1. `javac -cp "library/libarx-3.9.0.jar;bin" src/org/deidentifier/arx/test/AdultDataAnonymization.java -d bin/`
    1. `java -cp "library/libarx-3.9.0.jar;bin" org.deidentifier.arx.test.AdultDataAnonymization`
- Note: You need to have atleast JDK11.
- Here for visibility purposes, the output will be shown in the console as well as in the new CSV file which will be created in the `data` folder located in the root directory with the name `adult\_anonymized\_data.csv`.
- The dataset and hierarchies used in this project are sourced from the ARX official documentation. You can find the dataset and various hierarchies stored in the `data` folder located in the root directory.
- The `library` folder in the root directory consists of the ARX Java library needed for performing various operations using ARX API. You can find it [here](https://arx.deidentifier.org/downloads/).

### Challenges and processes followed

- Challenges
  - ARX has very little to no community support which makes the development process a tedious task especially when you’re stuck on something as googling won’t help and every search result on Google takes you to the documentation which actually is quite extensive, if referred carefully it makes the life easier for developers. I’m used to referring to documentation due to a scenario that occurred a couple of years back while doing a project on Hyperledger Fabric which is a blockchain framework by IBM and was deprecated midway during the development of my project.
  - Java and Eclipse IDE are something which I don’t use frequently, so setting up the project did take some time but thanks to my knowledge of Object-Oriented Programming it was an easy transition. Also, it was a good experience to work with a different IDE for a change(I normally use Visual Studio code).
- Process followed
  - I started by understanding what exactly is differential privacy and why is it so important, during this process I referred to a few blogs and youtube videos. After understanding this I looked up k-anonymity and its importance in the process of data anonymization. 
  - After getting a hang of the basic concepts, I went to ARX's official website and downloaded the graphical anonymization tool in order to understand exactly what this tool does and how. This was in my opinion the most important step which allowed me to better realize the working of this library whose later I was going to use the API of.
  - During the development process, I referred to ARX developer documentation and their corresponding examples which were sufficient to complete this project.
  
### Use cases and benefits

- Automotive innovation via car driver behavior analysis
- Preserving private information when organizations collaborate
- Helping retailers get more insight from customer data
- Longer data retention for improved business analysis
- Policing and data publishing in the public sector

### Future prospects

Differential privacy(DP) is a young field of study, and people are still learning how to utilize it successfully. Transitioning from research to practice, as well as controlling DP parameters, provide operational challenges. Is DP the ultimate way to accomplish a ‘privacy-utility trade-off’? The answer depends on the availability of generic domain-based algorithms and the opinion of regulatory bodies. If these issues are addressed, DP will surely emerge as the leading data anonymization platform for real-time data which might be in Terabytes or Petabytes, and the creation of ML/AI application models.

### References

1. [ARX documentation](https://arx.deidentifier.org/)
1. [K-anonymity](https://en.wikipedia.org/wiki/K-anonymity)
1. [ARX Github](https://github.com/arx-deidentifier/arx)
1. [Everything about k-anonymity - Immuta](https://www.immuta.com/articles/k-anonymity-everything-you-need-to-know-2021-guide/)
1. [Differential Privacy Data Anonymization - TCS](https://www.tcs.com/blogs/differential-privacy-data-anonymization)
1. [What is Differential Privacy?](https://www.youtube.com/watch?v=-JRURYTfBXQ)
1. [Introduction to the ARX Data Anonymization Tool](https://www.youtube.com/watch?v=N8I-sxmMfqQ)
1. [Linking Attack and K-Anonymity](https://www.youtube.com/watch?v=-GBhYvcIRCM)
1. [Differential Privacy - Simply Explained](https://www.youtube.com/watch?v=gI0wk1CXlsQ)
