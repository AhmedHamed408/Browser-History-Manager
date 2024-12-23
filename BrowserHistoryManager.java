import java.util.Scanner;

public class BrowserHistoryManager {

    static class Node {

        String url;
        Node prev, next;

        public Node(String url) {
            this.url = url;
            this.prev = null;
            this.next = null;
        }
    }

    static class BrowserHistory {

        private Node head;
        private Node current;

        public BrowserHistory(String homepage) {
            head = new Node(homepage);
            current = head;
        }

        public void visit(String url) {
            Node newNode = new Node(url);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
            System.out.println("Visited: " + url);
        }

        public String back(int steps) {
            while (steps > 0 && current.prev != null) {
                current = current.prev;
                steps--;
            }
            System.out.println("Back to: " + current.url);
            return current.url;
        }

        public String forward(int steps) {
            while (steps > 0 && current.next != null) {
                current = current.next;
                steps--;
            }
            System.out.println("Forward to: " + current.url);
            return current.url;
        }

        public String getCurrentPage() {
            return current.url;
        }

        public void displayHistory() {
            Node temp = head;
            int index = 1;
            System.out.println("Browsing History:");
            while (temp != null) {
                if (temp == current) {
                    System.out.println(index + ". " + temp.url + " (current)");
                } else {
                    System.out.println(index + ". " + temp.url);
                }
                temp = temp.next;
                index++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter First URL:");
        String homepage = scanner.nextLine();
        BrowserHistory browserHistory = new BrowserHistory(homepage);

        while (true) {
            System.out.println("\n      DashBoard");
            System.out.println("1) Visit a new URL");
            System.out.println("2) Current page");
            System.out.println("3) Back");
            System.out.println("4) Forward");
            System.out.println("5) Browsing history");
            System.out.println("6) Exit");
            System.out.print("\nChoose an option: ");

            String input = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter URL to visit:");
                    String url = scanner.nextLine();
                    browserHistory.visit(url);
                    break;

                case 2:
                    System.out.println("Current Page: " + browserHistory.getCurrentPage());
                    break;

                case 3:
                    System.out.println("Enter number of steps to go back:");
                    int backSteps = scanner.nextInt();
                    scanner.nextLine();
                    browserHistory.back(backSteps);
                    break;

                case 4:
                    System.out.println("Enter number of steps to go forward:");
                    int forwardSteps = scanner.nextInt();
                    scanner.nextLine();
                    browserHistory.forward(forwardSteps);
                    break;

                case 5:
                    browserHistory.displayHistory();
                    break;

                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    System.out.println("   See You Soon  ");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invaliad choice. Please, enter number in range 1 - 6");
            }
        }
    }
}
