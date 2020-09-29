# Duke project
## User guide
### Table of contents
* [QuickStart](#quickstart)
* [Features](#features)
  * [Adding a todo task](#todo)
  * [Adding a deadline task](#deadline)
  * [Adding an event task](#event)
  * [Listing all tasks in the task list](#list)
  * [Marking a task as done](#done)
  * [Removing a task from the list](#delete)
  * [Finding tasks with matching keyword(s)](#find)
  * [Finding tasks with matching date / date and time](#date)
  * [Exiting the program](#bye)
  * [Saving the data](#saving-the-data)
  * [Loading the data](#loading-the-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)

### QuickStart

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `jar` file from [here](https://github.com/xingrong123/ip/releases) 
3. Copy the file to the folder you want to use as the home folder.
4. Run the jar file in the `terminal`. 
5. Type the command in the command box and press `Enter` to execute it. 
    
### Features

> Notes about the command format:  
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.  
>       e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo homework`.
> * When inputting `DATE` or `TIME`, the user must follow a specific date and time format.  
>       `DATE` requires to be in `"d/M/yyyy"` format.  
>       e.g. `20/12/2020`, `4/2/1993`
>       `TIME` requires to be in `"HHmm"` 24H format.  
>       e.g. `2100` - 9.00 p.m., `0845` - 8.45 a.m.
> * Items in curly brackets with slashes between them are different inputs which 
>       the user must choose to input one of them.  
>       e.g. in `deadline DESCRIPTION /by {DATE/DATE TIME/DESCRIPTION_OF_DATETIME}`, 
>       the user must input one and only one of the three items in the curly brackets (`DATE`, `DATE TIME` or `DESCRIPTION`)
> * `TASK_NUMBER` refers to the number of the task which it is assigned to in the list.  
>       To find out the `TASK_NUMBER` of a task, use the `list` command and 
>       refer to the number on the left side of the task.  
>       `TASK_NUMBER` ranges from 1 to 100 inclusively and is an integer.

<a name="todo"></a>
#### Adding a todo task: `todo`

Adds a todo task to the task list.  
Format: `todo DESCRIPTION`  
Examples:  
* `todo test`
* `todo math homework`

<a name="deadline"></a>
#### Adding a deadline task: `deadline`

Adds a deadline task to the task list.  
Format: `deadline DESCRIPTION /by {DATE/DATE TIME/DESCRIPTION_OF_DATETIME}`  
Examples:  
* `deadline complete assignment /by 13/12/2039`
* `deadline complete project /by 5/2/2020 1200`
* `deadline complete project /by wed night`

<a name="event"></a>
#### Adding an event task: `event`

Adds an event task to the task list.  
Format: `event DESCRIPTION /at {DATE/DATE TIME/DESCRIPTION_OF_DATETIME}`  
Examples:  
* `event complete assignment /at 13/12/2039`
* `event complete project /at 5/2/2020 1200`
* `event complete project /at friday morning`

<a name="list"></a>
#### Listing all tasks in the task list: `list`

Shows a list of all tasks.  
Format: `list`  
Example:  
* `list`

<a name="done"></a>
#### Marking a task as done: `done`

Changes a state of a task to "done".  
Format: `done TASK_NUMBER`  
Examples:  
* `done 1`
* `done 23`

<a name="delete"></a>
#### Removing a task from the list: `delete`

Permanently removes a task from the list.  
Format: `delete TASK_NUMBER`  
Examples:  
* `delete 1`
* `delete 23`

<a name="find"></a>
#### Finding tasks with matching keyword(s): `find`

Shows a list of tasks with descriptions which contain the keyword(s).  
Format: `find KEYWORD`  
Examples:  
* `find math`
* `find assignment one`
* `find univers`

<a name="date"></a>
#### Finding tasks with matching date / date and time: `date`

Shows a list of scheduled tasks with matching date / date and time.  
Format: `date {DATE/DATE TIME}`  
Examples:  
* `date 21/12/2019`
* `date 21/12/2019 2359`

<a name="bye"></a>
#### Exiting the program: `bye`

Exits the program.  
Format: `bye`  
Example:  
* `bye`

#### Saving the data
TaskList data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

#### Loading the data
TaskList data are loaded from the hard disk automatically after starting the program. 
If the data file does not exist, an empty TaskList will be created instead.
There is no need to load manually.


### FAQ
**Q**: How do I transfer my data to another Computer?  
**A**: Copy and paste the `data/tasks.txt` file to the directory where the `jar` file is at. 
        Start the application and all the data should be loaded.

### Command summary
**Action** | **Format, Examples**
------------ | -------------
**todo**|`todo DESCRIPTION` <br> e.g., `todo math homework`
**deadline**|`deadline DESCRIPTION /by {DATE/DATE TIME/DESCRIPTION_OF_DATETIME}` <br> e.g., `deadline assignment /by 20/3/2020`
**event**|`event DESCRIPTION /at {DATE/DATE TIME/DESCRIPTION_OF_DATETIME}` <br> e.g., `event class /at 20/3/2020 0900`
**list**|`list`
**done**|`done TASK_NUMBER` <br> e.g., `done 3`
**delete**|`delete TASK_NUMBER` <br> e.g., `delete 12`
**find**|`find KEYWORD` <br> e.g., `find math`
**date**|`date {DATE/DATE TIME}` <br> e.g., `date 20/3/2020`
**exit**|`bye`