/*
 *    Copyright 2020 bithon.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.bithon.component.commons.utils;

/**
 * @author Frank Chen
 * @date 22/1/24 9:53 am
 */
public class HumanReadablePercentage extends Number {

    public static HumanReadablePercentage parse(String readableText) {
        return new HumanReadablePercentage(readableText);
    }

    private final double fraction;
    private final String readableText;

    public HumanReadablePercentage(String readableText) {
        Preconditions.checkNotNull(readableText, "readableText");

        readableText = readableText.trim();
        Preconditions.checkIfTrue(!readableText.isEmpty(), "readableText can't be EMPTY.");
        Preconditions.checkIfTrue(readableText.length() >= 2, "The format of readableText is illegal");

        Preconditions.checkIfTrue(readableText.charAt(readableText.length() - 1) == '%', "The percentage format must end with '%' character");

        String number = readableText.substring(0, readableText.length() - 1);
        try {
            this.readableText = readableText;
            this.fraction = Double.parseDouble(number) / 100;
        } catch (NumberFormatException e) {
            throw new Preconditions.InvalidValueException("The number [%s] in the text is malformed. ", number);
        }
    }

    public double getFraction() {
        return this.fraction;
    }

    @Override
    public String toString() {
        return this.readableText;
    }

    @Override
    public int intValue() {
        return (int) this.fraction;
    }

    @Override
    public long longValue() {
        return (long) this.fraction;
    }

    @Override
    public float floatValue() {
        return (float) this.fraction;
    }

    @Override
    public double doubleValue() {
        return this.fraction;
    }
}
