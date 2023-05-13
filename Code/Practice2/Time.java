class Time {
    private final int fourDigitTime;

    Time(int fourDigitTime) {
        this.fourDigitTime = fourDigitTime;
    }

    public int getTime() {
        return this.fourDigitTime;
    }

    public int getMinutesAfterMidnight() {
        int sum = 0;
        int curr = this.getTime();

        final int LAST_TWO_DIGIT = 100;
        final int HOURS_TO_MINUTES_MULTIPLIER = 60;

        sum += (curr % LAST_TWO_DIGIT);
        curr /= LAST_TWO_DIGIT;

        sum += (curr % LAST_TWO_DIGIT) * HOURS_TO_MINUTES_MULTIPLIER;
        return sum;
    }
}
