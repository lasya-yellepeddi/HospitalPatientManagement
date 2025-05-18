package hms;

class Patient implements Comparable<Patient> {

	private String name;
    private int id;
    private int condition;
    private long admissionTime;

    public Patient(String name, int id, int condition) {
        this.name = name;
        this.id = id;
        this.condition = condition;
        this.admissionTime = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getCondition() {
        return condition;
    }

    public long getAdmissionTime() {
        return admissionTime;
    }

    @Override
    public int compareTo(Patient other) {
        if (this.condition != other.condition) {
            return Integer.compare(other.condition, this.condition); // Higher condition value = higher priority
        } else {
            return Long.compare(this.admissionTime, other.admissionTime); // Earlier admission time = higher priority
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id + ", Condition: " + condition + ", Admission Time: " + admissionTime;
    }
}
