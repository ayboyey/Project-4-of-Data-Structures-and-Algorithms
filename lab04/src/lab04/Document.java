package lab04;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Document {
	
	public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }

    public void load(Scanner scan) {
        String input;
        while (!(input = scan.nextLine()).equalsIgnoreCase("eod")) {
            String[] arr = input.split("\\s+");
            for (String word : arr) {
                if (word.equalsIgnoreCase("eod")) {
                    return;
                }
                if (isCorrectLink(word.toLowerCase())) {
                    link.add(createLink(word.toLowerCase().substring(5)));
                }
            }
        }
    } 

    public boolean isCorrectLink(String link) {
        return link.toLowerCase().matches("link=[a-z]\\w*") || (link.matches("link=[a-z0-9]*\\([0-9]*\\)") && !link.matches("link=[a-z0-9]*\\(0\\)"));
    }

    public static boolean isCorrectId(String id) {
        return id.toLowerCase().matches("[a-z]\\w*");
    }

    
    public static Link createLink(String link) {
        if (link.matches("[a-z0-9]*\\([0-9]*\\)")) {
            int start = link.indexOf("(") + 1;
            String key = link.toLowerCase().substring(0, start - 1);
            int weight = Integer.parseInt(link.substring(start, link.length() - 1));
            return new Link(key, weight);
        } else {
            return new Link(link.toLowerCase());
        }
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        Iterator<Link> str = link.iterator();
        output.append("Document: " + name);
        int count = 10;
        while (str.hasNext()) {
            String seperator = "";
            if (count == 10) {
                seperator = "";
                output.append("\n");
            } else if (count != 0) {
                seperator = " ";
            } else {
                seperator = "\n";
            }
            count--;
            output.append(seperator).append(str.next().toString());
        }
        return output.toString();
    }

    public String toStringReverse() {
        String retStr = "Document: " + name;
        return retStr + link.toStringReverse();
    }
}