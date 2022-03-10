package eternity.math;

import com.expression.parser.Parser;
import com.expression.parser.exception.CalculatorException;
import eternity.exception.EmptyInputException;
import eternity.exception.InvalidInputException;

import java.io.*;
import java.util.*;

public class UI {
	private static List<String> history = new ArrayList<>();
	private static List<String> quotes = new ArrayList<>();
	private static List<String> authors = new ArrayList<>();
	private static Decimal decimal = new Decimal();

	public static void main(String[] args) {
		readQuotes("/eternity/resources/motivation.txt", quotes);
		readQuotes("/eternity/resources/authors", authors);

		Scanner scanner = new Scanner(System.in);
		printTitle();

		menuLoop: 
		while (true) {
			printMainMenu();
			String menuInput = scanner.nextLine();
			menuInput = menuInput.replaceAll("\\s", "");

			try {
				if (menuInput.equals("") || menuInput == null)
					throw new EmptyInputException();

				else if (menuInput.equals("1")) {
					// start algebra calculator
					algebraLoop: 
					while (true) {
						try {
							printAlgebraCalculatorMenu();
							String algebraInput = scanner.nextLine();
							algebraInput = algebraInput.replaceAll("\\s", "");

							if (algebraInput.equals("") || algebraInput == null)
								throw new EmptyInputException();

							else if (algebraInput.equalsIgnoreCase("m"))
								break algebraLoop;

							else if (algebraInput.equalsIgnoreCase("q"))
								printClosingMessage();

							else if (algebraInput.equalsIgnoreCase("h"))
								printHistory();
							
							else if (algebraInput.equalsIgnoreCase("c"))
								clearHistory();

							else {
								try {
									Double rawAnswer = Parser.eval(algebraInput).getValue();
									Double answerWithDecimal = decimal.getResultDecimal(rawAnswer);

									String result = algebraInput + " = " + answerWithDecimal;
									System.out.println(result);
									history.add(result);

								} catch (CalculatorException e) {
									System.out.println(e.getMessage());

								} catch (NumberFormatException e) {
									System.out.println("MATH ERROR");
								}
							}
						} catch (EmptyInputException e) {
							System.out.println(e.getMessage());
						}
					}
					// end algebra calculator
				} else if (menuInput.equals("2")) {
					// start finance calculator
					financeLoop: 
					while (true) {
						try {
							printFinanceCalculatorMenu();
							String financeInput = scanner.nextLine();
							financeInput = financeInput.replaceAll("\\s", "");

							if (financeInput.equals("") || financeInput == null)
								throw new EmptyInputException();

							else if (financeInput.equalsIgnoreCase("m"))
								break financeLoop;

							else if (financeInput.equalsIgnoreCase("q"))
								printClosingMessage();

							else if (financeInput.equalsIgnoreCase("h"))
								printHistory();
							
							else if (financeInput.equalsIgnoreCase("c"))
								clearHistory();

							else if (financeInput.equalsIgnoreCase("1")) {
								// start MAD
								MADLoop: 
								while (true) {
									try {
										printMADMenu();
										String MADinput = scanner.nextLine();

										if (MADinput.equalsIgnoreCase("") || MADinput == null)
											throw new EmptyInputException();

										else if (MADinput.equalsIgnoreCase("m"))
											break MADLoop;

										else if (MADinput.equalsIgnoreCase("q"))
											printClosingMessage();

										else if (MADinput.equalsIgnoreCase("h"))
											printHistory();
										
										else if (MADinput.equalsIgnoreCase("c"))
											clearHistory();

										else if (MADinput.matches("[0-9\\s.]+")) {
											List<String> listOfStringInputs = new ArrayList<>();
											listOfStringInputs = Arrays.asList(MADinput.split("\\s"));

											ArrayList<Double> listOfDoubleInputs = new ArrayList<>();
											listOfStringInputs
													.forEach(string -> listOfDoubleInputs.add(Double.valueOf(string)));

											Double rawAnswer = Functions.meanAbsoluteDeviation(listOfDoubleInputs);
											String result = "Mean Absolute Deviation of (";

											for (int i = 0; i < listOfStringInputs.size(); i++) {
												result += listOfStringInputs.get(i);

												if (i != listOfStringInputs.size() - 1)
													result += ", ";
												else
													result += ")";
											}
											Double answerWithDecimal = decimal.getResultDecimal(rawAnswer);
											result += " = " + answerWithDecimal;
											history.add(result);
											System.out.println(result);
										} else
											throw new InvalidInputException("Invalid input");
									} catch (EmptyInputException e) {
										System.out.println(e.getMessage());
									} catch (InvalidInputException e) {
										System.out.println(e.getMessage());
									} catch (NumberFormatException e) {
										System.out.println("Invalid input detected");
									}
								}
								// end MAD
							} else if (financeInput.equalsIgnoreCase("2")) {
								// start STD
								STDLoop: 
								while (true) {
									try {
										printSTDMenu();
										String STDinput = scanner.nextLine();
										if (STDinput.equalsIgnoreCase("") || STDinput == null) {
											throw new EmptyInputException();
										} else if (STDinput.equalsIgnoreCase("m")) {
											break STDLoop;
										} else if (STDinput.equalsIgnoreCase("q")) {
											printClosingMessage();
										} else if (STDinput.equalsIgnoreCase("h")) {
											printHistory();
										} else if (STDinput.equalsIgnoreCase("c")) {
											clearHistory();
										} else if (STDinput.matches("[0-9\\s.]+")) {
											List<String> listOfStringInputs = new ArrayList<>();
											listOfStringInputs = Arrays.asList(STDinput.split("\\s"));
											ArrayList<Double> listOfDoubleInputs = new ArrayList<>();
											listOfStringInputs
													.forEach(string -> listOfDoubleInputs.add(Double.valueOf(string)));
											Double rawAnswer = Functions.std_dev(listOfDoubleInputs);
											String result = "Standard Deviation of (";
											for (int i = 0; i < listOfStringInputs.size(); i++) {
												result += listOfStringInputs.get(i);
												if (i != listOfStringInputs.size() - 1) {
													result += ", ";
												} else {
													result += ")";
												}
											}
											Double answerWithDecimal = decimal.getResultDecimal(rawAnswer);
											result += " = " + answerWithDecimal;
											history.add(result);
											System.out.println(result);
										} else
											throw new InvalidInputException("Invalid input");
									} catch (EmptyInputException e) {
										System.out.println(e.getMessage());
									} catch (InvalidInputException e) {
										System.out.println(e.getMessage());
									} catch (CalculatorException e) {
										System.out.println(e.getMessage());
									} catch (NumberFormatException e) {
										System.out.println("Invalid input detected");
									}
								}
								// end STD
							} else {
								try {
									throw new InvalidInputException();

								} catch (InvalidInputException e) {
									System.out.println(e.getMessage());
								}
							}
						} catch (EmptyInputException e) {
							System.out.println(e.getMessage());
						}
					}
					// end financial calculator
				} else if (menuInput.equalsIgnoreCase("3")) {
					// start settings
					settingLoop:
						while (true) {
						printSettingsMenu();
						String settingInput = scanner.nextLine();

						if (settingInput.equalsIgnoreCase("") || settingInput == null)
							throw new EmptyInputException();

						else if (settingInput.equalsIgnoreCase("1")) {
							// start decimal
							decimalLoop: 
								while (true) {
								System.out.println("\nDecimals");
								System.out.println(
										"Please enter the number of decimal places you would like your result to have: ");

								String decimalStringInput = scanner.nextLine();
								int decimalIntInput = 0;
								try {
									decimalIntInput = Integer.parseInt(decimalStringInput);
									decimal.setDecimal(decimalIntInput);
									System.out.println(
											"The calculator has been set to " + decimalIntInput + " decimal places.");
									break settingLoop;

								} catch (NumberFormatException e) {
									System.out.println("Invalid input");
								}
							}
							// start decimal
						} else if (settingInput.equalsIgnoreCase("2")) {
							// start degreeRad
							degreeRadLoop: 
								while (true) {
								System.out.println("\nDegree/Radian");
								System.out.println(" - 1 for Degree \n" + " - 2 for Radian \n");

								String degRadStringInput = scanner.nextLine();
								if (degRadStringInput.equalsIgnoreCase("") || degRadStringInput == null)
									throw new EmptyInputException();

								else if (degRadStringInput.equalsIgnoreCase("1")) {
									DegreeRadian.setRadian(false);
									System.out.println("The calculator has been set to Degree mode");
									break settingLoop;

								} else if (degRadStringInput.equalsIgnoreCase("2")) {
									DegreeRadian.setRadian(true);
									System.out.println("The calculator has been set to Radian mode");
									break settingLoop;

								} else
									System.out.println("Invalid input");
							}
							// end degreeRad
						} else {
							System.out.println("Invalid input");
						}
					}
					// end settings
				} else if (menuInput.equalsIgnoreCase("q"))
					printClosingMessage();

				else
					System.out.println("Invalid input");
			} catch (EmptyInputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void printTitle() {
		System.out.println("****************************************");
		System.out.println("*                                      *");
		System.out.println("*               ETERNITY               *");
		System.out.println("*                                      *");
		System.out.println("****************************************");
		System.out.println("           Welcome to ETERNITY!         ");
	}

	private static void printMainMenu() {
		System.out.println("\n");
		System.out.println("\t---------------------------");
		System.out.println("\t--       Main Menu       --");
		System.out.println("\t---------------------------");
		System.out.println(" - 1 for Algebra calculator \n"
				+ " - 2 for Finance calculator (Mean Absolute Deviation and Standard Deviation) \n"
				+ " - 3 for Settings \n" + " - q to exit the program \n");
		System.out.print("Let us know what you want to do: ");
	}

	private static void printAlgebraCalculatorMenu() {
		System.out.println("\n");
		System.out.println("\t---------------------------");
		System.out.println("\t--   Algebra Calculator  --");
		System.out.println("\t---------------------------");
		System.out.println(
				" - m to go back to main menu \n" + " - h to see your history \n" + " - c to clear your history \n" + " - q to exit the program \n");
		System.out.print("Please enter your equation and hit enter: ");
	}

	private static void printFinanceCalculatorMenu() {
		System.out.println("\n");
		System.out.println("\t---------------------------");
		System.out.println("\t--   Finance Calculator  --");
		System.out.println("\t---------------------------");
		System.out.println(" - 1 for Mean Absolute Deviation \n" + " - 2 for Standard Deviation \n"
				+ " - m to go back to main menu \n" + " - h to see your history \n" + " - c to clear your history \n"+ " - q to exit the program \n");
		System.out.print("Please select the function that you want: ");
	}

	private static void printMADMenu() {
		System.out.println("\n");
		System.out.println("\t---------------------------");
		System.out.println("\t--Mean Absolute Deviation--");
		System.out.println("\t---------------------------");
		System.out.println(
				" - m to go back to previous menu \n" + " - h to see your history \n" + " - c to clear your history \n"+ " - q to exit the program \n");
		System.out.println("Please enter the series of your inputs separated by a space, and hit Enter when done: \n");
	}

	private static void printSTDMenu() {
		System.out.println("\n");
		System.out.println("\t---------------------------");
		System.out.println("\t--   Standard Deviation  --");
		System.out.println("\t---------------------------");
		System.out.println(
				" - m to go back to previous menu \n" + " - h to see your history \n" + " - c to clear your history \n"+ " - q to exit the program \n");
		System.out.println("Please enter the series of your inputs separated by a space, and hit Enter when done: \n");
	}

	private static void printSettingsMenu() {
		System.out.println("\n");
		System.out.println("\t---------------------------");
		System.out.println("\t--       Settings        --");
		System.out.println("\t---------------------------");
		System.out.println(" - 1 for Decimals \n" + " - 2 for Degree/Radian \n");
		System.out.println("Please select your option: ");
	}

	private static void printHistory() {
		System.out.println("\n");
		System.out.println("\t---------------------------");
		System.out.println("\t--        History        --");
		System.out.println("\t---------------------------");
		System.out.println("Here is the history of your previous calculations: ");

		if (history.size() == 0) {
			System.out.println("EMPTY");

		} else {
			for (String s : history)
				System.out.println(s);
		}

		System.out.println();
	}
	
	private static void clearHistory() {
		history.clear();
		System.out.println("** History cleared **");
	}

	private static void printClosingMessage() {
		System.out.println("\n");
		System.out.println("\t---------------------------");
		System.out.println("\t--   Quote of the day    --");
		System.out.println("\t---------------------------");

		int random = new Random().nextInt(quotes.size());
		System.out.println(quotes.get(random));
		System.out.println("-" + authors.get(random));
		System.out.println("\n");

		System.out.println("Program shutting down...");
		System.out.println("Thank you for using ETERNITY.");

		history.clear();
		System.exit(0);
	}

	private static void readQuotes(String filePath, List<String> list) {
		InputStream in = UI.class.getResourceAsStream(filePath);
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(in));
			String st;
			while ((st = br.readLine()) != null) {
				list.add(st);
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");

		} catch (IOException e) {
			System.out.println("IO Exception");
		}
	}
}
