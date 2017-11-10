//Given an absolute path for a file (Unix-style), simplify it.

//For example,
//path = "/home/", => "/home"
//path = "/a/./b/../../c/", => "/c"


public String simplifyPath(String path) {
	//建立一个双向的double-ended-queue
    Deque<String> stack = new LinkedList<>();
    Set<String> skip = new HashSet<>(Arrays.asList("..",".","")); //“”表示多个slash("/")，留下一个就好
    for(String dir : path.split("/")){
    	//".."表示，例如a/b/../c 是从b返回到a再从a到c，所以simplify后是a/c。因此此时应pop出一个没有用的。
        if(dir.equals("..") && !stack.isEmpty()) stack.pop();
        else if(!skip.contains(dir)) stack.push(dir);
    }
    String res = "";
    for (String dir : stack) res = "/" + dir + res;
    return res.isEmpty()? "/" : res;
}