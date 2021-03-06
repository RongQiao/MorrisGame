
public class MiniMaxOpeningBlack{
	public static void main(String[] args) {
		boolean argsValid = MorrisBase.checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			
			String inputPosStr = UIController.readFromFile(inFN);
			String swapedPosStr = MorrisBase.swapPosStr(inputPosStr);
			MiniMaxOpening MMO = new MiniMaxOpening(swapedPosStr);
			int depth = Integer.parseInt(depthStr);
			if (MMO.checkDepth(depth)) {
				MMO.play(depth);
				String playResult = MMO.getResultStr();
				swapedPosStr = MorrisBase.swapPosStr(playResult);
				UIController.writeToFile(outFN, swapedPosStr);
				String respStr[] = MMO.orgPlayResultResponse();
				respStr[0] = "Board Position: " + swapedPosStr;
				MMO.showPlayResult(respStr);				
			}
			else {
				System.out.println("search depth is too much.");
			}
			
		}
		if (!argsValid) {
			System.out.println("invalid args.");
		}
	}


}
