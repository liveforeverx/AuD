package ex1;

public class PalindromeAlternative {
    public static boolean isPalindrome(String text) {
        String newtext = text.toLowerCase();
        int start = 0, end = newtext.length() - 1;
        boolean result = true;
        while(start < end)
        {
            // Skip all nicht Buchstaben am Anfang
            while(isIt(newtext.charAt(start)) != true) start++;
            // Skip all nicht Buchstaben am Ende
            while(isIt(newtext.charAt(end)) != true) end--;
            if(newtext.charAt(start++) != newtext.charAt(end--)) result = false;
        }
        return result;
    }
        
    private static boolean isIt(char x){
        return (x >= 'a' && x <= 'z') ? true : false;
    }
    
    public static void main(String[] args) {
        System.out.println("Reliefpfeiler: " + isPalindrome("Reliefpfeiler"));
        System.out.println("Madam: " + isPalindrome("Madam"));
        System.out.println("Lagerregal: " + isPalindrome("Lagerregal"));
        System.out.println("Na, Fakir, Paprika-Fan?: " + isPalindrome("Na, Fakir, Paprika-Fan?"));
        System.out.println("Reliefpfeler: " + isPalindrome("Reliefpfeler"));
    }
}
