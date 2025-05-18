package hms;
import java.util.*;

public class Hospital {
	
	private Queue<Patient> queue;
    private PriorityQueue<Patient> priorityQueue;
    private List<Patient> admissionRecords;
    private List<Patient> dischargeRecords;
    private Set<Integer> patientIds;

    public Hospital() {
        this.queue = new LinkedList<>();
        this.priorityQueue = new PriorityQueue<>();
        this.admissionRecords = new ArrayList<>();
        this.dischargeRecords = new ArrayList<>();
        this.patientIds = new HashSet<>();
    }

    public void admitPatient(Patient patient) {
        for (Patient p : admissionRecords) {
            if (p.getId() == patient.getId()) {
                System.out.println("\nPatient with ID " + patient.getId() + " already exists. Cannot admit the same patient again.");
                return;
            }
        }
        queue.add(patient);
        priorityQueue.add(patient);
        admissionRecords.add(patient);
        System.out.println("\nPatient " + patient.getName() + " admitted.");
    }



    public void dischargePatient() {
        if (queue.isEmpty()) {
            System.out.println("\nNo patients to discharge.");
            return;
        }
        Patient patient = queue.poll();
        priorityQueue.remove(patient);
        dischargeRecords.add(patient);
        patientIds.remove(patient.getId());
        System.out.println("\nPatient " + patient.getName() + " discharged.");
    }

    public void prioritizePatient() {
        if (queue.isEmpty()) {
            System.out.println("\nNo patients to prioritize.");
            return;
        }
        List<Patient> patients = new ArrayList<>(queue);
        Collections.sort(patients); // Sort based on condition severity and admission time
        queue.clear();
        queue.addAll(patients);
        System.out.println("\nPatients prioritized based on severity and admission time.");
    }

    public void displayQueue() {
        System.out.println("\nCurrent Patient Queue:");
        for (Patient patient : queue) {
            System.out.println(patient);
        }
    }

  /*  public void displayPriorityList() {
        System.out.println("Prioritized Patient List:");
        for (Patient patient : priorityQueue) {
            System.out.println(patient);
        }
    }
    */

    public void displayRecords() {
        System.out.println("\n\n====================================================================================================================");
        System.out.println("Admission Records:");
        if (admissionRecords.isEmpty()) {
            System.out.println("\nNo patients admitted.");
        } else {
            for (Patient patient : admissionRecords) {
                System.out.println(patient);
            }
        }

        System.out.println("\n---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Discharge Records:");
        if (dischargeRecords.isEmpty()) {
            System.out.println("\nNo patients discharged.");
        } else {
            for (Patient patient : dischargeRecords) {
                System.out.println(patient);
            }
         System.out.println("====================================================================================================================");
        }
    }

    public boolean isUniqueId(int id) {
        return !patientIds.contains(id);
    }

    public static void main(String[] args) {
    	System.out.println(">>>>>>>>>>>>>>> HOSPITAL MANAGEMENT SYSTEM <<<<<<<<<<<<<<<");
        Hospital hospital = new Hospital();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
        	try {
            System.out.println("\n1. ADMIT PATIENT");
            System.out.println("2. DISCHARGE PATIENT");
            System.out.println("3. PRIORITIZE PATIENTS");
            System.out.println("4. DISPLAY PATIENT QUEUE");
            //System.out.println("5. Display Prioritized Patient List");
            System.out.println("5. DISPLAY ADMISSION/DISCHARGE RECORDS");
            System.out.println("6. EXIT");
            System.out.print("\n\nEnter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character after the number
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a valid integer.");
            scanner.nextLine(); // consume the invalid input
        } finally {
            // Optional: Any code you want to always run after try/catch
        }
            switch (choice) {
                case 1:
                	String name;
                    do {
                        System.out.print("\nEnter patient name: ");
                        name = scanner.nextLine().trim();
                        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                            System.out.println("\nInvalid name. Please enter a name using only alphabetic characters.");
                        }
                    } while (name.isEmpty() || !name.matches("[a-zA-Z ]+"));

                    String idStr;
                    int id = -1;
                    boolean validId;
                    do {
                        validId = true;
                        System.out.print("Enter patient ID: ");
                        idStr = scanner.nextLine().trim();
                        if (idStr.isEmpty() || !idStr.matches("\\d+")) {
                            System.out.println("\nInvalid ID format. Please enter a numeric ID.");
                            validId = false;
                        } else {
                            try {
                                id = Integer.parseInt(idStr);
                            } catch (NumberFormatException e) {
                                System.out.println("\nInvalid ID format. Please enter a numeric ID.");
                                validId = false;
                            }
                        }
                    } while (!validId);

                    int condition = 0;
                    do {
                    	try {
                            System.out.print("Enter patient condition severity (1-5): ");
                            condition = scanner.nextInt();
                            if (condition < 1 || condition > 5) {
                                System.out.println("Please enter severity in the specified range i.e 1-5\n.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer between 1 and 5.");
                            scanner.nextLine(); // consume the invalid input
                        } finally {
                            // Optional: Any code you want to always run after try/catch
                        }
                    } while (condition < 1 || condition > 5);
                    scanner.nextLine();  

                    Patient patient = new Patient(name, id, condition);
                    hospital.admitPatient(patient);
                    break;

                case 2:
                    hospital.dischargePatient();
                    break;
                case 3:
                    hospital.prioritizePatient();
                    break;
                case 4:
                    hospital.displayQueue();
                    break;
               /* case 5:
                    hospital.displayPriorityList();
                    break;
                    */
                case 5:
                    hospital.displayRecords();
                    break;
                case 6:
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

	}
