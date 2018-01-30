package kriptoklasik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class kriptoklasik {
	
	
	
	private static String fileinput = "input.txt";
	private static String fileoutput = "output.txt";
	
	static ArrayList<Byte> plain = new ArrayList<Byte>();	//plaintext keseluruhan
	static ArrayList<Byte> cipher = new ArrayList<Byte>();	//ciphertext viginere
	static ArrayList<Character> cipherplay = new ArrayList<Character>();	//ciphertext playfair
	static ArrayList<Character> plainplay= new ArrayList<Character>();	//plaintext playfair
	static ArrayList<Character> kunci = new ArrayList<Character>();	//kunci enkripsi/dekripsi
	
	
	
	public static void main(String[] args) throws IOException {
    
        kriptoklasik kk = new kriptoklasik();
        Scanner scanner = new Scanner(System.in);
        Scanner scanline = new Scanner(System.in);
        
        System.out.print("Pilihan Aksi (1. Enkripsi 2. Dekripsi) : ");
		int pilihanaksi = scanner.nextInt();
		
		System.out.println("Pilihan Algoritma: ");
		System.out.println("1. Viginere Alfabet");
		System.out.println("2. Viginere Ekstended: ");
		System.out.println("3. Payfair");
		int pilihanalgo = scanner.nextInt();
		
		
		if(pilihanaksi == 1) {
			System.out.print("Input plaintext : (1. Papan Ketik 2. File) : ");
			int pilihaninput = scanner.nextInt();
			
			if(pilihaninput == 1) {
				System.out.println("Silakan ketik plaintext melalui papan ketik: ");
				String pkplain = scanline.nextLine();
				for (char c : pkplain.toCharArray()) {
			        plain.add((byte) c);
			    }
				System.out.println("Silakan ketik kunci enkripsi: ");
				String pkkunci = scanline.nextLine();
			    for (char c : pkkunci.toCharArray()) {
			        kunci.add(c);
			    }
			}else {
				System.out.println("Silakan masukkan nama file yang akan dienkripsi: ");
				//fileinput = scanline.nextLine();
				byte[] arrplain = Files.readAllBytes(Paths.get(fileinput));
				for (byte b : arrplain) {
				    plain.add(b);
				}
				System.out.println("Silakan ketik kunci enkripsi: ");
				String pkkunci = scanline.nextLine();
				for (char c : pkkunci.toCharArray()) {
			        kunci.add(c);
			    }
			}
			//Jalankan fungsi Enkripsi
			if (pilihanalgo == 1) {
				cipher = kk.enkripsiviginere1(plain,kunci);
			}else if(pilihanalgo == 2) {
				cipher = kk.enkripsiviginere2(plain,kunci);
			}else {
				String skunci = "ABCDEFGHIJKLMNOPRSTUVWXYZ";
			    char[] ckunci = skunci.toCharArray();

			    kunci = new ArrayList<Character>();
			    for (char c : ckunci) {
			        kunci.add(c);
			    }
			    
				char[][] isi = new char[7][7];
				HashMap<Character, ArrayList<Integer>> alamat = new HashMap<Character, ArrayList<Integer>>();
				kk.isitabelkunci(isi, alamat, kunci);
				
				for (int baris = 0 ; baris < 7 ; baris++) {
					for (int kolom= 0 ; kolom < 7 ; kolom++) {
						System.out.print(isi[baris][kolom]);
						System.out.print(" ");
					}
					System.out.println();
				}
				
				int ll;
				char[] plainfair = new char[plain.size()];
				for (int i = 0 ; i < plain.size() ; i++) {
					ll = plain.get(i);
					plainfair[i] = (char) ll;
				}
				
				cipherplay = kk.enkripsiplayfair(plainfair,isi,alamat);
			}
			
		}else {
			System.out.print("Input ciphertext : (1. Papan Ketik 2. File) : ");
			int pilihaninput = scanner.nextInt();
			if(pilihaninput == 1) {
				System.out.println("Silakan ketik ciphertext melalui papan ketik: ");
				String pkcipher = scanline.nextLine();
				for (char c : pkcipher.toCharArray()) {
			        cipher.add((byte) c);
			    }
				System.out.println("Silakan ketik kunci dekripsi: ");
				String pkkunci = scanline.nextLine();
			    for (char c : pkkunci.toCharArray()) {
			        kunci.add(c);
			    }
			}else {
				System.out.println("Silakan masukkan nama file yang akan didekripsi: ");
				//fileinput = scanline.nextLine();
				byte[] arrplain = Files.readAllBytes(Paths.get(fileinput));
				for (byte b : arrplain) {
				    cipher.add(b);
				}
				System.out.println("Silakan ketik kunci dekripsi: ");
				String pkkunci = scanline.nextLine();
				for (char c : pkkunci.toCharArray()) {
			        kunci.add(c);
			    }
			}
			//Jalankan fungsi Dekripsi
			if (pilihanalgo == 1) {
				cipher = kk.dekripsiviginere1(plain,kunci);
			}else if(pilihanalgo == 2) {
				cipher = kk.dekripsiviginere2(plain,kunci);
			}else {
				String skunci = "ABCDEFGHIJKLMNOPRSTUVWXYZ";
			    char[] ckunci = skunci.toCharArray();

			    kunci = new ArrayList<Character>();
			    for (char c : ckunci) {
			        kunci.add(c);
			    }
			    
				char[][] isi = new char[7][7];
				HashMap<Character, ArrayList<Integer>> alamat = new HashMap<Character, ArrayList<Integer>>();
				kk.isitabelkunci(isi, alamat, kunci);
				
				for (int baris = 0 ; baris < 7 ; baris++) {
					for (int kolom= 0 ; kolom < 7 ; kolom++) {
						System.out.print(isi[baris][kolom]);
						System.out.print(" ");
					}
					System.out.println();
				}
				
				int ll;
				char[] cipherfair = new char[cipher.size()];
				for (int i = 0 ; i < cipher.size() ; i++) {
					ll = cipher.get(i);
					cipherfair[i] = (char) ll;
				}
				System.out.println(cipherfair);
				plainplay = kk.dekripsiplayfair(cipherfair,isi,alamat);
			}
		}
		System.out.println("===============================================");
		System.out.println("===============================================");
		System.out.println("Panjang plaintext :"+ plain.size());
		System.out.println(plain.toString());
		
		System.out.println("===============================================");
		System.out.println("===============================================");
		
		System.out.println("HASIL ENKRIPSI");
		
		if(pilihanalgo == 1 || pilihanalgo == 2) {
			System.out.println("PANJANG CIPHER " + cipher.size());
			System.out.println(cipher.toString());
			
			byte[] outcipher = new byte[cipher.size()];
			for(int i = 0 ; i < cipher.size(); i++) {
				outcipher[i] = cipher.get(i);
			}
			
			System.out.println("===============================================");
			System.out.println("===============================================");
		}else {
			
			System.out.println("HASIL ENKRIPSI PLAYFAIR");
			
			System.out.println("PANJANG CIPHER " + plainplay.size());
			System.out.println(plainplay.toString());
			
//			char[] outcipher2 = new char[cipherplay.size()];
//			for(int i = 0 ; i < cipherplay.size(); i++) {
//				outcipher2[i] = cipherplay.get(i);
//			}
			
			char[] outcipher2 = new char[plainplay.size()];
			for(int i = 0 ; i < plainplay.size(); i++) {
				outcipher2[i] = plainplay.get(i);
			}
			
			FileWriter fw = new FileWriter(fileoutput);
			BufferedWriter bw = new BufferedWriter(fw);
			
			try {
				fw = new FileWriter(fileoutput);
				bw = new BufferedWriter(fw);
				bw.write(outcipher2);
			} catch (IOException e) {
	
				e.printStackTrace();
	
			} finally {
	
				try {
	
					if (bw != null)
						bw.close();
	
					if (fw != null)
						fw.close();
	
				} catch (IOException ex) {
	
					ex.printStackTrace();
	
				}
	
			}
		}
	}
	
	// ENKRIPSI ALFABET SAJA
	public ArrayList<Byte> enkripsiviginere1(ArrayList<Byte> plaintext, ArrayList<Character> kunci) {
		ArrayList<Byte> ciphertext = new ArrayList<Byte>();
		
		int temp;
		int indeks = 0;
		for(int i = 0 ; i < plaintext.size() ; i++) {
			if(plaintext.get(i)<65 || plaintext.get(i)>90) {
				ciphertext.add(plaintext.get(i));
			}else {
				temp = (plaintext.get(i) - 65 + (int) kunci.get(indeks) - 65) % 26 + 65;
				ciphertext.add((byte) temp);
				indeks++;
			}
			if (indeks == kunci.size()) {
				indeks = 0;
			}
		}
		
		return ciphertext;
	}
	
	// DEKRIPSI ALFABET SAJA
	public ArrayList<Byte> dekripsiviginere1(ArrayList<Byte> ciphertext, ArrayList<Character> kunci) {
		ArrayList<Byte> plaintext = new ArrayList<Byte>();
		
		int temp;
		int indeks = 0;
		for(int i = 0 ; i < ciphertext.size(); i++) {
			if(ciphertext.get(i)<65 || ciphertext.get(i)>90) {
				plaintext.add(ciphertext.get(i));
			}else {
				temp = (ciphertext.get(i) - (int) kunci.get(indeks)) % 26 + 65;
				if(temp<65) {
					temp += 26;
				}
				plaintext.add((byte) temp);
				indeks++;
			}
			if (indeks == kunci.size()) {
				indeks = 0;
			}
		}
		
		return plaintext;
	}
	
	// ENKRIPSI EXTENDED ASCII
	public ArrayList<Byte> enkripsiviginere2(ArrayList<Byte> plaintext, ArrayList<Character> kunci) {
		ArrayList<Byte> ciphertext = new ArrayList<Byte>();
		
		int temp;
		int indeks = 0;
		for(int i = 0 ; i < plaintext.size() ; i++) {
			
			temp = (plaintext.get(i) + (int) kunci.get(indeks) - 65);
			ciphertext.add((byte) temp);
			indeks++;
		
			if (indeks == kunci.size()) {
				indeks = 0;
			}
		}
		
		return ciphertext;
	}
	
	// DEKRIPSI EXTENDED ASCII
	public ArrayList<Byte> dekripsiviginere2(ArrayList<Byte> ciphertext, ArrayList<Character> kunci) {
		ArrayList<Byte> plaintext = new ArrayList<Byte>();
		
		int temp;
		int indeks = 0;
		for(int i = 0 ; i < ciphertext.size(); i++) {
			
			temp = (ciphertext.get(i) - (int) kunci.get(indeks) + 65);
			plaintext.add((byte) temp);
			indeks++;
			
			if (indeks == kunci.size()) {
				indeks = 0;
			}
		}
		
		return plaintext;
	}
	
	
	public void isitabelkunci(char[][] isi, HashMap<Character, ArrayList<Integer>> alamat, ArrayList<Character> k) {
		
		int index = 0;
		
		for (int baris = 1 ; baris < 6 ; baris++) {
			for (int kolom= 1 ; kolom < 6 ; kolom++) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(baris);
				temp.add(kolom);
				alamat.put(k.get(index),temp);
				isi[baris][kolom] = k.get(index);
				index++;
			}
		}
		
		for (int baris = 1 ; baris < 6 ; baris++) {
			isi[baris][6] = isi[baris][1];
			isi[baris][0] = isi[baris][5];
		}
		for (int kolom = 1 ; kolom < 6 ; kolom++) {
			isi[6][kolom] = isi[1][kolom];
			isi[0][kolom] = isi[5][kolom];
		}
	}
	
	
	//Enkripsi Algoritma Playfair
	public ArrayList<Character> enkripsiplayfair(char[] plaintext, char[][] isi, HashMap<Character, ArrayList<Integer>> alamat) {
		 
		ArrayList<Character> res = new ArrayList<Character>();
		
		
		ArrayList<Character> p2 = new ArrayList<Character>();
		 for (int i = 0 ; i < plaintext.length ; i++) {
			 if(plaintext[i]>=65 && plaintext[i]<=90) {
				 if(plaintext[i] == 'Q') {
					 p2.add('K');
				 }else {
					 p2.add(plaintext[i]);
				 }
			 }
		 }
		 int j = 0;
		 char c1 ;
		 char c2 ;
		 
		 ArrayList<Character> alc = new ArrayList<Character>();
		 
		 while (j < p2.size()) {
			 c1 = p2.get(j);
			 if(j == p2.size()-1) {
				 c2 = 'Z';
			 }else {
				 c2 = p2.get(j+1); 
			 }
			 if(c1 != c2) {
				 alc.add(c1);
				 alc.add(c2);
			 }else {
				 alc.add(c1);
				 alc.add('Z');
				 j -= 1;
			 }
			 j += 2;
		 }
		 
		 int k = 0;
		 int baris1 =0;
		 int kolom1 =0;
		 int baris2 =0;
		 int kolom2 =0;
		 
		 System.out.println("ALC : "+alc);
				 
		 while (k < alc.size()) {
			 
			 baris1 = alamat.get(alc.get(k)).get(0);
			 kolom1 = alamat.get(alc.get(k)).get(1);
			 baris2 = alamat.get(alc.get(k+1)).get(0);
			 kolom2 = alamat.get(alc.get(k+1)).get(1);
			 
			 System.out.println(baris1+" "+ kolom1);
			 System.out.println(baris2+" "+ kolom2);
			 if(baris1 == baris2) {
				 res.add(isi[baris1][kolom1+1]);
				 res.add(isi[baris2][kolom2+1]);
			 }else if(kolom1 == kolom2) {
				 res.add(isi[baris1+1][kolom1]);
				 res.add(isi[baris2+1][kolom2]);
			 }else {
				 res.add(isi[baris1][kolom2]);
				 res.add(isi[baris2][kolom1]);
			 }
			 k += 2;
		 }
		 
		 return res;
	}
	
	//Dekripsi Algoritma Playfair
	public ArrayList<Character> dekripsiplayfair(char[] ciphertext, char[][] isi, HashMap<Character, ArrayList<Integer>> alamat) {
		 
		ArrayList<Character> res = new ArrayList<Character>();
		
		 int k = 0;
		 int baris1 =0;
		 int kolom1 =0;
		 int baris2 =0;
		 int kolom2 =0;
		 
		 System.out.println(ciphertext);
				 
		 while (k < ciphertext.length) {
			 
			 baris1 = alamat.get(ciphertext[k]).get(0);
			 kolom1 = alamat.get(ciphertext[k]).get(1);
			 baris2 = alamat.get(ciphertext[k+1]).get(0);
			 kolom2 = alamat.get(ciphertext[k+1]).get(1);
			 
			 System.out.println(baris1+" "+ kolom1);
			 System.out.println(baris2+" "+ kolom2);
			 if(baris1 == baris2) {
				 res.add(isi[baris1][kolom1-1]);
				 res.add(isi[baris2][kolom2-1]);
			 }else if(kolom1 == kolom2) {
				 res.add(isi[baris1-1][kolom1]);
				 res.add(isi[baris2-1][kolom2]);
			 }else {
				 res.add(isi[baris1][kolom2]);
				 res.add(isi[baris2][kolom1]);
			 }
			 k += 2;
		 }
		 
		 
		 return res;
	}
	
}
