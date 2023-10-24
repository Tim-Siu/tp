package seedu.address.model.util;

import static seedu.address.commons.util.DateTimeParserUtil.parseStringToDateTime;
import static seedu.address.model.util.CompanySampleDataUtil.getSampleCompanies;
import static seedu.address.model.util.PersonSampleDataUtil.getSamplePersons;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.company.Company;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipDescription;
import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.model.company.internship.InternshipName;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {


    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Company sampleCompany : getSampleCompanies()) {
            sampleAb.addCompany(sampleCompany);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an internship set based the list of maps given.
     */
    @SafeVarargs
    public static Set<Internship> getInternshipSet(Map<String, String>... maps) {
        Set<Internship> internships = new HashSet<>();

        for (Map<String, String> map : maps) {
            String roleName = map.get("roleName");
            String description = map.get("description");
            String interviewDateTimeStr = map.get("interviewDateTime");

            InternshipName roleNameObj = new InternshipName(roleName);
            InternshipDescription descriptionObj = new InternshipDescription(description);

            if (interviewDateTimeStr != null) {
                InternshipInterviewDateTime interviewDateTimeObj =
                        new InternshipInterviewDateTime(parseStringToDateTime(interviewDateTimeStr));
                internships.add(new Internship(roleNameObj, descriptionObj, interviewDateTimeObj));
            } else {
                internships.add(new Internship(roleNameObj, descriptionObj));
            }
        }

        return internships;
    }
}
