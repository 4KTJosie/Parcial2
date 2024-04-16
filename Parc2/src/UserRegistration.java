import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

class UserRegistration {
    private static final String NAME_PATTERN = "^[a-zA-Z\\s]+$";
    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    private static final List<User> registeredUsers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Mostrar usuarios registrados");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente

            switch (option) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    displayRegisteredUsers();
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String firstName = scanner.nextLine();
        if (!validateName(firstName)) {
            System.out.println("Nombre inválido. Debe contener solo letras y espacios.");
            return;
        }

        System.out.print("Ingrese su apellido: ");
        String lastName = scanner.nextLine();
        if (!validateName(lastName)) {
            System.out.println("Apellido inválido. Debe contener solo letras y espacios.");
            return;
        }

        System.out.print("Ingrese su correo electrónico: ");
        String email = scanner.nextLine();
        if (!validateEmail(email)) {
            System.out.println("Correo electrónico inválido.");
            return;
        }

        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        if (!validatePassword(password)) {
            System.out.println("Contraseña inválida. Debe tener al menos 8 caracteres, incluir caracteres especiales, al menos dos letras mayúsculas, tres letras minúsculas y un número.");
            return;
        }

        User user = new User(firstName, lastName, email, password);
        registeredUsers.add(user);
        System.out.println("Usuario registrado exitosamente.");
    }

    private static void displayRegisteredUsers() {
        if (registeredUsers.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("\nUsuarios registrados:");
            for (User user : registeredUsers) {
                System.out.printf("Nombre: %s %s, Correo electrónico: %s\n", user.getFirstName(), user.getLastName(), user.getEmail());
            }
        }
    }

    private static boolean validateName(String name) {
        return Pattern.matches(NAME_PATTERN, name);
    }

    private static boolean validateEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    private static boolean validatePassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }
}