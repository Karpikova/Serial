package library.Modules;

/**
 * Created by makar on 05.04.2017.
 */
public class Reader {
    private String firstname;
    private String secondname;
    private String lastname;

    public Reader(String firstname, String secondname, String lastname, long passportNumber) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.passportNumber = passportNumber;
    }

    public long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(long passportNumber) {
        this.passportNumber = passportNumber;
    }

    private long passportNumber;

    @Override
    public int hashCode() {
        return (int) passportNumber * 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof  Reader))
            return false;

        if (this.passportNumber != ((Reader) obj).passportNumber)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", passportNumber=" + passportNumber +
                '}';
    }
}
