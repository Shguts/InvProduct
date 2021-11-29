class Validation {
        fun CheckString(getStr:String?):Boolean = getStr!=null&&Regex("\\s*[A-Za-z_]\\w*\\s*").matches(getStr)
        fun Chekvar(getDouble:String?):Boolean = getDouble!=null&&Regex("\\d+.\\d+").matches(getDouble)
}