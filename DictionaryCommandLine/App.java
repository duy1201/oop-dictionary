import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        DictionaryManagement dictionary = new DictionaryManagement();
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        int select;
        dictionary.getFromTxt();//Lay du lieu tu file

        do {
            System.out.println("--------------------");
            System.out.println("(1) Look up\n"
                                + "(2) Add word\n"
                                + "(3) Delete word\n"
                                + "(4) Show all words\n"
                                + "(0) Exit\n" + "");
            System.out.print("Your answer: ");
            select = sc.nextInt();
            sc.nextLine();
            switch (select) {

                case 0: { //thoat
                    end = true;
                    dictionary.saveToTxt();//Luu du lieu vao file
                    break; 
                }

                case 1: { //Tra tu
                    System.out.println("(Look up)");
                    boolean again = true;
                    while (again) {
                        System.out.println("Word:");
                        String word = sc.nextLine();
                        String meaning = dictionary.lookUp(word);
                        if (meaning == null) {
                            System.out.println("This word has no meaning!");
                        }
                        else {
                            System.out.println("Meaning: " + meaning);
                        }

                        System.out.println("Do you want to look up again? (1.Yes/0.No)");
                        int answer = sc.nextInt();
                        sc.nextLine();
                        if (answer == 0) {
                            again = false;
                        }
                    }
                    break;
                }

                case 2: { //them tu
                    System.out.println("(Add word)");
                    System.out.print("Number of words: ");
                    int numberOfWords = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < numberOfWords; i++) {
                        System.out.println("Insert word:");
                        String word = sc.nextLine();
                        System.out.println("Insert meaning:");
                        String meaning = sc.nextLine();
                        dictionary.addWord(word, meaning);
                    }
                    break;
                }

                case 3: { //xoa tu
                    System.out.println("(Delete Word)");
                    boolean again = true;
                    while (again) {
                        System.out.println("Word:");
                        String word = sc.nextLine();
                        if (dictionary.lookUp(word) == null) {//Khong tim thay tu can tim
                            System.out.println("Can't find this word!");
                        }
                        else {
                            dictionary.deleteWord(word);
                        }    
                        System.out.println("Do you want to delete word again? (1.Yes/0.No)");
                        int answer = sc.nextInt();
                        sc.nextLine();
                        if (answer == 0) {
                            again = false;
                        }
                    }
                    break;
                }
                
                case 4: { //in danh sach tu
                    System.out.println("(Show all words)");
                    boolean again = true;
                    while(again) {
                        dictionary.showAllWords();
                        System.out.println("(Press any to exit)");
                        int answer = sc.nextInt();
                        sc.nextLine();
                        if (answer == 0) {
                            again = false;
                        }
                    }
                    break;
                }
            }
        } while (!end);
        sc.close();
    }
}
