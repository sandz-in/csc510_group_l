#README for Expense Tracking with Improved Input
# CSC510 Group L
## Idea
Expense tracker with improved inputs mechanism

**Links**
* [Issues] (https://github.com/sandz-in/csc510_group_l/issues)
* [Milestones] (https://github.com/sandz-in/csc510_group_l/milestones)
* [Contributors] (https://github.com/sandz-in/csc510_group_l/graphs/contributors)

Demo Time: 3/4/2016 10:00 AM

**Solutions**
* [Solution 1](https://github.com/sandz-in/csc510_group_l/tree/master/ImageClicker): Optical Character Recognition. Captures the receipt and extracts the amount
* [Solution 2](https://github.com/sandz-in/csc510_group_l/tree/master/Demo): Voice based Input: Listens for the user to speak the total amount and identify the digit.
* [Solution 3](https://github.com/sandz-in/csc510_group_l/tree/master/smsFeed): Identify the expense via SMS. SMS sent by the bank is read and the total amount is extracted.
* [Solution 4](https://github.com/sandz-in/csc510_group_l/tree/master/ExpenseSharingInterface): Improved Keyboard compared to Splitwise.

**Telemetry**
* We collect the expense submission method used for every request as metadata from the application.
* This metadata is captured in the backend against which we run queries to gather facts such as most frequently used method for tracking expenses in the application.
* Also, the number of delete actions are captured. This can be used to measure the approxmiate accuracy of the solution.

**Evaluation Plan**

The given solutions will be evaluated by a comparative study of their accuracy and usage preference. An input is considered to be accurate if the user is able to provide the input to the application without editing it. Frequent usage of a particular solution, shows user preference for that solution.

There are two methods of telemetry implemented for further evaluation -

* Telemetry evaluating user preference:
  The logs are collected from the app that tell us which solution was used for each input. Frequent use of a solution indicates its user preference for it.

* Telemetry tracking the number of deletes:
  The logs also keep track of the number of times delete button was pressed which indicates the number of times the user needed to correct the input provided by the solution. The number of deletes are inversely proportional to the accuracy of the solution.

* Telemetry for Time taken (Future implementation)
  Logs can also be made to track the time taken for each solution used. This will test the time taken for each input method.

Evaluation can also be done by taking feedback from the users who initially participated in the survey for the project. The users can evaluate the different solutions based on their accuracy, ease of usage and whether the solutions are able to mitigate the earlier drawbacks of the cost sharing applications.
