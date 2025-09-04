import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

class Reservation {
    private int roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation(int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Erro: a data de saída deve ser depois da data de entrada.");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int duration() {
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public void updateDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate now = LocalDate.now();

        if (checkIn.isBefore(now) || checkOut.isBefore(now)) {
            throw new IllegalArgumentException("Erro: as datas de atualização devem ser futuras.");
        }
        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Erro: a data de saída deve ser depois da data de entrada.");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Reserva: Quarto "
                + roomNumber
                + ", check-in: " + checkIn.format(fmt)
                + ", check-out: " + checkOut.format(fmt)
                + ", " + duration() + " noites";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.print("Número do quarto: ");
            int roomNumber = sc.nextInt();
            sc.nextLine();

            System.out.print("Data de entrada (dd/MM/yyyy): ");
            LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);

            System.out.print("Data de saída (dd/MM/yyyy): ");
            LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println(reservation);

            System.out.println("\nEntre com os dados para atualizar a reserva:");
            System.out.print("Nova data de entrada (dd/MM/yyyy): ");
            checkIn = LocalDate.parse(sc.nextLine(), fmt);

            System.out.print("Nova data de saída (dd/MM/yyyy): ");
            checkOut = LocalDate.parse(sc.nextLine(), fmt);

            reservation.updateDates(checkIn, checkOut);
            System.out.println(reservation);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Erro na reserva: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
        finally {
            sc.close();
        }
    }
}
