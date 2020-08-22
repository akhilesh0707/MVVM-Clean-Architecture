package com.aqube.truecaller.domain.interector.blogs


internal object BlogFactory {
    //region --------------------------------------------------------------------------------------
    // Common const
    const val SUCCESS = "<html><body>Blog return</body></html>"
    const val EXCEPTION = "Exception"
    const val EMPTY = ""

    // Character at const
    const val CHAR_INDEX = 3
    const val ERROR = "Params character index can't be null!"
    const val CHAR = "m"

    // Every Character at const
    const val EVERY_CHAR_INDEX = 7
    const val EVERY_ERROR = "Params character at index can't be null!"
    const val EVERY_CHAR = "<ludm"

    // Character count cont
    const val CHAR_COUNT = "<html><body>Blog = \t1\n" +
            "return</body></html> = \t1\n"
    //endregion -----------------------------------------------------------------------------------
}