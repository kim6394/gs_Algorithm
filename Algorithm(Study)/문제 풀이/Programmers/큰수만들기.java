class 큰수만들기 {
    public static String solution(String number, int k) {
        
		StringBuilder sb = new StringBuilder(number);
		
		for (int cnt = 0; cnt < k; cnt++) {
			
            int i;
			
			for (i = 0; i < sb.length()-1; i++) {
				if(sb.charAt(i) < sb.charAt(i+1)) {
					break;
				}
			}
			sb.deleteCharAt(i);
            
		}
		return sb.toString();
    }
}
