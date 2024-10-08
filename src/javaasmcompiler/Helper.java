package javaasmcompiler;


import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static java.util.List<String> normalizeInstructions(String instructions) {
        List<String> instructionsList = new ArrayList<>();
        final String[] lines = instructions.split("\n");
        for (int i = 0; i < lines.length; i++) {
            int index = lines[i].indexOf('#');
            if (index != -1) {
                lines[i] = lines[i].substring(0, index);  // Keep everything before '#'
            }
            lines[i] = lines[i].trim();
        }
        for (String line : lines) {
            if (line.length() == 0)
                continue;
            boolean firstSpace = false;
            String ins = "";
            if (line.contains(":")) {
                int j = 0;
                while (line.charAt(j) != ':') ins += line.charAt(j++);
                int z = ins.length() - 1;
                while ((z > 0) && (ins.charAt(z) == ' ')) {
                    ins = ins.substring(0, ins.length() - 1);
                    z = ins.length() - 1;
                }
                ins += ": ";
                j++;
                while ((j < line.length()) && (line.charAt(j) == ' '))
                    j++;
                for (; j < line.length(); j++) {
                    if (line.charAt(j) != ' ' ||
                            (line.charAt(j) == ' ' && !firstSpace)) {
                        ins += line.charAt(j);
                        if (line.charAt(j) == ' ')
                            firstSpace = true;
                    }
                }
            } else {
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) != ' ' ||
                            (line.charAt(j) == ' ' && !firstSpace)) {
                        ins += line.charAt(j);
                        if (line.charAt(j) == ' ')
                            firstSpace = true;
                    }
                }
            }
            instructionsList.add(ins);
        }
        return instructionsList;
    }

}
