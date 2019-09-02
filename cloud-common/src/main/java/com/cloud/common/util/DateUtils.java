package com.cloud.common.util;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.Date;

/**
 *
 */
public class DateUtils {

      /**
       * 根据生日算年龄
       *
       * @param birthday
       * @return
       */
      public static int getAgeByBirthdayForJoda(Date birthday) {
            LocalDate birthdayDate = new LocalDate(birthday);
            LocalDate now = new LocalDate();
            Years age = Years.yearsBetween(birthdayDate, now);
            return age.getYears();
      }


}
