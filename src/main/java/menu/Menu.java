package menu;

public final class Menu {

    public static final String MAIN_MENU = "Main menu" +
            "\n_________________________" +
            "\n1) Add task;\n" +
            "2) Show all tasks;\n" +
            "3) Exit.";

    public static final String ADD_MENU = "\nAdd new task menu" +
            "\n_________________________" +
            "\nPlease enter information such as: " +
            "name, end date and priority " +
            "\n\nRequirements:" +
            "\nAll info should be split with \";\" symbol" +
            "\nInput date format: YYYY-MM-DD" +
            "\nAvailable priority: low, medium, high" +
            "\n\nExample: Buy tickets;2016-24-10;medium\n";

    public static final String TASK_LIST_HEADER = "\nTask list:\n___________________";
    public static final String TASK_ID = "\nId:  ";
    public static final String TASK_NAME = "\nName: ";
    public static final String TASK_DATE = "\nEnd date: ";
    public static final String TASK_PRIORITY = "\nPriority: ";
    public static final String TASK_OVERDUE = "\nTask is overdue!";
    public static final String HORIZONTAL_LINE = "\n___________________";

    public static final String TASK_LIST_MENU = "\n1) Indicate completed task; \n" +
            "2) Show all completed tasks; \n" +
            "3) Back to main menu. \n";

    public static final String LIST_COMPLETED_HEADER = "\nList completed tasks:" +
            "\n___________________";


    public static final String LIST_COMPLETED_TASK_MENU = "\n1) Back to previous menu;" +
            "\n2) Back to main menu.";

    public static final String CLOSE_TASK_MENU = "Enter task id, that you have completed: ";

    public static final String SUCCESSFUL_ADDED_TASK = "Task were added successfully!\n";
    public static final String INCORRECT_INPUT = "Incorrect input. Try again";
    public static final String COMPLETED_TASK = "Task now completed with id = ";


}
