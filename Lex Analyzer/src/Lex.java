import java.nio.file.Files;
import java.nio.file.Paths;

/**	Name:	Iqra Mumtaz
 * 	Roll No.: L14-4121
 * 	Course (Section): Compiler Construction (CS)
 * 	BSCS (FAST-NUCES)
 * 	l144121@lhr.nu.edu.pk
 */

/**	Developing a Lexical Analyzer for a new language C-- 
 * 	Input C-- Source Code
 * 	Output : Generate TWO text files :	
 * 	(a)	words.txt: generated sequence of all token-lexeme pairs encountered in the given .cm file
 * 	(b)	symboltable.txt: the names of all the program identifiers
 */

public class Lex {
	static String sourceCode = new String();
	static StringBuffer sourceCodeBuffer = new StringBuffer();

	public Lex() {}

	public static void removingComments() {
		int indexBegin = 0;
		while (true) {
			indexBegin = sourceCode.indexOf("#", indexBegin);

			if ( sourceCode.charAt(indexBegin+1) == '#' ) {			//removing multi-line comments
				int j = sourceCode.indexOf("#", indexBegin+2);
				sourceCodeBuffer.delete(indexBegin, j+2);
				String str = new String (sourceCodeBuffer);					
			}
			if (indexBegin >= 0 ) { 	//removing single-line comments
				int j = sourceCode.indexOf("\n", indexBegin);
				sourceCodeBuffer.delete(indexBegin, j);
				String str = new String (sourceCodeBuffer);
				sourceCode = str;
			}
			else {
				break;
			}
		}
	}
	
	public static boolean catchErrors() {
		
		int index = sourceCode.indexOf("$");
		
		if ( index > 0) {	
			int index2 = sourceCode.indexOf('\n', index);
			int index3 = sourceCode.lastIndexOf('\n', index);
			
			System.out.println("Error in LINE:  " +sourceCode.substring(index3, index2));
			return true;	//display error line and stop compilation
		}
		System.out.println("There's NO error in the code. Compiled succesfully.");
		return false;
	}	
	
	public static void main(String[] args) throws Exception {

		System.out.println("Hello World!");
		
		System.out.println(args[0] +" "+ args[1]);

		if ( (args.length != 2) ||  !(args[0].contentEquals("cmm")) || !(args[1].contains(".cm")) )  {			
			System.out.println("Command NOT found.");
			return;
		}
		
		//if command is FOUND then read source code from file
		//try block will run after user given correct CMD command i.e. cmm prog1.cm
		try {
			
			//getting source code from user entered file name
			String fileName = new String(args[1]);
			fileName = fileName+".txt";	
			
			//String sourceCode = new String ( Files.readAllBytes (Paths.get( fileName)));	//string sourceCode stores whole source code present in file.cm
			//StringBuffer sourceCodeBuffer = new StringBuffer(sourceCode);				
			
			sourceCode = new String ( Files.readAllBytes (Paths.get( fileName)));	//string sourceCode stores whole source code present in file.cm
			sourceCodeBuffer = new StringBuffer(sourceCode);
			
			System.out.println("\nC-- Source Code : \n\n" +sourceCode);	//displaying source code on console for developer
			
			//Delete comments from the source code because compiler (Lex) has nothing to do with these
			removingComments();
			System.out.println("\nSource code after removing comments: \n\n" +sourceCode);
			
			//Lex doesn't catch much errors. C-- doesn't have $ sign so if there's $ in code then Lex will not compile code and show error. $ can be a part of comments so comments are removed first
	//		boolean error = catchErrors();
		//	if (error) {	//if there's error in the code then display error line and return
			//	return;
			//}		
			
			}finally {
		}
	}
}