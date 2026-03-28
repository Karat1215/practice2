package part5.part5_2;

// ========== RECORD TEMPERATURE ==========
record Temperature(double value, Unit unit) {

    enum Unit {
        CELSIUS, FAHRENHEIT, KELVIN
    }

    public Temperature {
        double kelvin = toKelvin(value, unit);
        if (kelvin < 0) {
            throw new IllegalArgumentException("Температура не может быть ниже абсолютного нуля (0 K)");
        }
    }

    private double toKelvin(double value, Unit unit) {
        return switch (unit) {
            case CELSIUS -> value + 273.15;
            case FAHRENHEIT -> (value - 32) * 5 / 9 + 273.15;
            case KELVIN -> value;
        };
    }

    private double fromKelvin(double kelvin, Unit targetUnit) {
        return switch (targetUnit) {
            case CELSIUS -> kelvin - 273.15;
            case FAHRENHEIT -> (kelvin - 273.15) * 9 / 5 + 32;
            case KELVIN -> kelvin;
        };
    }

    public Temperature convertTo(Unit targetUnit) {
        if (this.unit == targetUnit) {
            return this;
        }
        double kelvin = toKelvin(this.value, this.unit);
        double newValue = fromKelvin(kelvin, targetUnit);
        return new Temperature(newValue, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, switch (unit) {
            case CELSIUS -> "°C";
            case FAHRENHEIT -> "°F";
            case KELVIN -> "K";
        });
    }
}

// ========== ENUM MATHOPERATION ==========
enum MathOperation {
    ADD {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY {
        @Override
        public double apply(double a, double b) {
            return a * b;
        }
    },
    DIVIDE {
        @Override
        public double apply(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Деление на ноль");
            }
            return a / b;
        }
    };

    public abstract double apply(double a, double b);
}

// ========== MAIN CLASS ==========
public class RecordEnumDemo {

    public static void main(String[] args) {
        Temperature body = new Temperature(36.6, Temperature.Unit.CELSIUS);
        System.out.println(body);
        System.out.println(body.convertTo(Temperature.Unit.FAHRENHEIT));
        System.out.println(body.convertTo(Temperature.Unit.KELVIN));

        System.out.println();

        double a = 10, b = 3;
        for (MathOperation op : MathOperation.values()) {
            System.out.printf("%s(%.0f, %.0f) = %.2f%n", op.name(), a, b, op.apply(a, b));
        }
    }
}