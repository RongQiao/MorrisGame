
public class MiniMaxGameBlack {

	public static void main(String[] args) {
		boolean argsValid = MorrisBase.checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			
			String inputPosStr = UIController.readFromFile(inFN);
			String swapedPosStr = MorrisBase.swapPosStr(inputPosStr);
			MiniMaxGame MMG = new MiniMaxGame(swapedPosStr);
			int depth = Integer.parseInt(depthStr);
			if (MMG.checkDepth(depth)) {
				MMG.play(depth);
				String playResult = MMG.getResultStr();
				swapedPosStr = MorrisBase.swapPosStr(playResult);
				UIController.writeToFile(outFN, swapedPosStr);
				String respStr[] = MMG.orgPlayResultResponse();
				respStr[0] = "Board Position: " + swapedPosStr;
				MMG.showPlayResult(respStr);				
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
