package subnets;

//dado um IP address space e uma mascara de sub-rede (subnet mask), da-nos o numero de hosts que temos disponiveis
//
// exemplo 1: 
// ----------
// IP address space = 192.168.0.0
// subnet mask      = 255.255.255.0
// 
// o numero de hosts é obtido pela formula 2^h - 2, em que h é o numero de bits = 0 na subnetmask (2^h lê-se 2 elevado a h, não confundir com o operador XOR do java)
// para isso é preciso transformar a subnet mask no seu formato binario
// no caso do nosso exemplo, 255.255.255.0 = 11111111.11111111.11111111.0000000
// portanto, 2^8 - 2 = 254, tratam-se dos endereços de 192.168.0.1 a 192.168.0.254
// o endereço 192.168.0.0 é o subnet identifier e o 192.168.0.255 é o network broadcast address


// exemplo 2
// ----------
//IP address space = 192.168.0.0
//subnet mask      = 255.255.254.0
//
// 255.255.254.0 = 11111111.11111111.11111110.0000000
// 2^9 - 2 = 510
// endereços de 192.168.0.1 a 192.168.1.254
// o endereço 192.168.0.0 é o subnet identifier e o 192.168.1.255 é o network broadcast address


public class NumberOfHosts {
	
	private String ipAddressSpace, subNetMask;    // no formato, por exemplo, 192.168.0.0
	
	private String networkSubnetIdentifier; 
	
	private long numberOfHosts;
	
	private int[] ipClasses = new int[4];
	
	int index1, index2;
	
	// construtor
	public NumberOfHosts (String ipAddressSpace, String subNetMask) {
		
		this.ipAddressSpace = ipAddressSpace;
		this.subNetMask = subNetMask;
		
		 
	    index1 = 0;
	    index2 = this.ipAddressSpace.indexOf(".", 0);
	    
	    // ipClasses[0] = this.ipAddressSpace.substring(0, index1 - 1);
	    this.ipAddressSpace.substring(0, index1 - 1);
	    
	}
	
	// MÉTODOS
	
	// transforma um numero decimal no seu equivalente binario. ex: de '255' para '11111111'
	private static String numToBin8(int numDecimal) {
		
		char[] ipNumToBinario = {'0', '0', '0', '0', '0', '0', '0', '0'};
		
		int dividendo, resto, quociente, ind;
		dividendo = numDecimal;
		
		quociente = 1;  // inicializa para entrar no while
		ind = 8;
		
		while (quociente != 0) {    
			ind --;
			resto = dividendo % 2;
			quociente = dividendo / 2;
			
			
			if (resto == 1)
				ipNumToBinario[ind] = '1';
			else
				ipNumToBinario[ind] = '0';
			
			dividendo = quociente;
		}
		
		
		return String.valueOf(ipNumToBinario);
		
	}
	
	
	// testa
	public static void main(String[] args) {
		
		// System.out.println(numToBin8(255));
		
		String addressSpaceip = "255.255.249.0";
		StringBuilder binAddressSpaceip = new StringBuilder();
		
		//if (addressSpaceip.contains(".")) {
		//	System.out.println("Ok");
		//}
		
		
		// addressSpaceip.replaceAll("\\.", "@");
		// System.out.println(addressSpaceip);
		
		String [] classes = addressSpaceip.split("\\.", 4); // parte a string pelo caracter '.', devolve um array com as varias partes 

		for (String classe : classes) {
			System.out.println("r: " + classe);
			String binn = numToBin8(Integer.parseInt(classe));
			System.out.println("l : " + binn);
			binAddressSpaceip.append(binn + ".");
			
		}
		binAddressSpaceip.deleteCharAt(binAddressSpaceip.length() - 1); // apaga o ultimo ponto
		
		System.out.println("b : " + binAddressSpaceip);
		
		// desde o formato binario, conta o numero de zeros a começar do menos significativo (direita), até encontrar um '1'
		char zero = '0';
		int numZeros = 0;
		for (int i = binAddressSpaceip.length() - 1 ; i >= 0; i--) {
			if (binAddressSpaceip.charAt(i) == zero) {
				numZeros ++;
			}
			else {
				break;
			}
				
		}
		
		// calcula o numero de ip que podemos ter
		System.out.println("Numero de Zeros: " + numZeros);
		int numHosts = (int)Math.pow(2, numZeros) - 2;
		System.out.println("Numero de IPs possiveis: " + numHosts);
		
		

	}
	
	
	

}
