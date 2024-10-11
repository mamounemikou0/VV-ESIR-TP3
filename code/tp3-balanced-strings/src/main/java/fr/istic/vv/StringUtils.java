package fr.istic.vv;

import java.util.Stack;

public class StringUtils {


    //  vérifier si une chaîne est équilibrée
    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            // Si c'est ouvrant, on le pousse dans la pile
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Si c'est fermant, on vérifie qu'il correspond à l'ouverture
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) return false;  // Pas d'ouvrant correspondant
                
                char open = stack.pop();
                if (!isMatchingPair(open, ch)) return false;  // Paire non correspondante
            }
        }

        // La pile doit être vide if tous les symboles ouvrants ont leurs  fermeture correspondante
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') || 
               (open == '{' && close == '}') || 
               (open == '[' && close == ']');
    }

}
