package library.Modules;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

/**
 * Created by makar on 05.04.2017.
 */
public class Booking{
    private BookInstance bookInstance;
    private Reader reader;
    private Date startDate;
    private Date returnDate;
    private Date finishDate;

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    @Override
    public int hashCode() {
        return bookInstance.hashCode() + reader.hashCode() + startDate.hashCode();
    }

    public BookInstance getBookInstance() {
        return bookInstance;
    }

    public Reader getReader() {
        return reader;
    }

    @Override

    public boolean equals(Object obj) {
        if (obj == null)
            return false;


        if (!(obj instanceof  Booking))
            return false;

        if (!(this.bookInstance.equals(((Booking) obj).bookInstance))
            || !(this.reader.equals(((Booking) obj).reader))
                || !(this.startDate.equals(startDate)))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookInstance=" + bookInstance +
                ", reader=" + reader +
                ", startDate=" + startDate +
                ", returnDate=" + returnDate +
                ", finishDate=" + finishDate +
                '}';
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Booking(BookInstance bookInstance, Reader reader, Date startDate, Date finishDate) {

        this.bookInstance = bookInstance;
        this.reader = reader;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

}
