//Validate if a given string is numeric.

//Some examples:
//"0" => true
//" 0.1 " => true
//"abc" => false
//"1 a" => false
//"2e10" => true

public boolean isNumber(String s) {
    s = s.trim();
    //set some flags.
    boolean pointSeen = false;
    boolean eSeen = false;
    boolean numberSeen = false;
    boolean numberAfterE = true;
    for(int i=0; i<s.length(); i++) {
        if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
            numberSeen = true;
            numberAfterE = true;
        } else if(s.charAt(i) == '.') {
            if(eSeen || pointSeen) { //only can see '.' if no other 'e' or '.'
                return false;
            }
            pointSeen = true;
        } else if(s.charAt(i) == 'e') {
            if(eSeen || !numberSeen) { // only can see 'e' if no other 'e' but has number
                return false;
            }
            numberAfterE = false;
            eSeen = true;
        } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
            if(i != 0 && s.charAt(i-1) != 'e') { // only can see '+'/'-' in the beginning or after 'e'
                return false;
            }
        } else {
            return false;
        }
    }

    return numberSeen && numberAfterE;
}