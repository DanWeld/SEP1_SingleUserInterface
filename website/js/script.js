$.get("xml/pets.xml", function (xml, status) {
  let pets = $(xml).find("pets");
  // Bootstrap row with gap between cards
  let row = $('<div class="row g-3"></div>');

  pets.each(function () {
    let pet = $(this);

    // Extract pet information
    let petName = pet.find("name").text() || "Unnamed pet";
    let petSpecies = pet.find("species").text() || "Unknown species";
    let petBreed = pet.find("breed").text() || null;
    let petType = pet.find("type").text() || null;
    let petColor = pet.find("color").text() || "Unknown color";
    let petGender =
      pet.find("gender").text().toLowerCase() === "m" ? "Male" : "Female";
    let priceText = pet.find("price").text();
    let petPrice = parseFloat(priceText) || 0;

    // Handle birth date and age
    let year = pet.find("birthDate > year").text();
    let month = pet.find("birthDate > month").text();
    let day = pet.find("birthDate > day").text();
    let petBirthdate =
      year && month && day
        ? `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`
        : "Unknown birthdate";

    // Calculate age based on birth date, using ageCalculator function.
    let age =
      petBirthdate !== "Unknown birthdate"
        ? ageCalculator(petBirthdate)
        : "Unknown age";

    // Filter only pets with a price greater than 0
    if (petPrice > 0) {
      let card = $(`
        <div class="col-md-2"> <!-- Bootstrap column for 5 cards per row -->
          <div class="card bg-light h-100">
            <div class="card-body">
              <h5 class="card-title">${petName}</h5>
              <p class="card-text"><strong>Species:</strong> ${petSpecies}</p>
              ${
                petBreed
                  ? `<p class="card-text"><strong>Breed:</strong> ${petBreed}</p>`
                  : ""
              }
              ${
                petType
                  ? `<p class="card-text"><strong>Type:</strong> ${petType}</p>`
                  : ""
              }
              <p class="card-text"><strong>Color:</strong> ${petColor}</p>
              <p class="card-text"><strong>Gender:</strong> ${petGender}</p>
              <p class="card-text"><strong>Age:</strong> ${age}</p>
              <p class="card-text"><strong>Price:</strong> ${petPrice.toFixed(
                2
              )}</p>
            </div>
          </div>
        </div>
      `);
      row.append(card);
    }
  });

  $("#pets").append(row);
});

/**
 * Calculates the age based on the birth date.
 * @param {string} birthDate - The birth date in the format 'YYYY-MM-DD'.
 * @returns {string} The age in years and months.
 */
function ageCalculator(birthDate) {
  let today = new Date();
  let birth = new Date(birthDate);
  let ageByMonth = today.getMonth() - birth.getMonth();
  let year = today.getFullYear() - birth.getFullYear();
  let age = "";
  // check if the birth date has not occurred yet this year
  if (
    ageByMonth < 0 ||
    (ageByMonth === 0 && today.getDate() < birth.getDate())
  ) {
    year--;
    ageByMonth = 12 + ageByMonth;
  }

  // check if the age is less than a year, then display the age in months
  if (year === 0) {
    age = `${ageByMonth} months`;
  } else {
    age = `${year} years and ${ageByMonth} months`;
  }

  return age;
}

/**
 * parse the xml file and store the reservations in an array.
 * each reservation is an object with a start date and an end date.
 * when the user clicks the check availability button, the user's start and end dates are compared to the reservations.
 * if there are more than 10 reservations that overlap with the user's dates, the user is alerted that there are no available spaces.
 * otherwise, the user is alerted with the number of available spaces.
 */
$(document).ready(function () {
  $.get("xml/reservations.xml", function (xml) {
    // array to store the reservations
    let reservations = [];

    // parse the xml file and store the reservations in an array
    $(xml)
      .find("reservations")
      .each(function () {
        let startYear = $(this).find("startDate > year").text();
        let startMonth = $(this).find("startDate > month").text();
        let startDay = $(this).find("startDate > day").text();

        let endYear = $(this).find("endDate > year").text();
        let endMonth = $(this).find("endDate > month").text();
        let endDay = $(this).find("endDate > day").text();
        // check if all the dates are valid
        if (
          startYear &&
          startMonth &&
          startDay &&
          endYear &&
          endMonth &&
          endDay
        ) {
          let startDate = new Date(startYear, startMonth - 1, startDay);
          let endDate = new Date(endYear, endMonth - 1, endDay);
          // store the reservation in the array
          reservations.push({ startDate, endDate });
        }
      });

    // check availability button click event
    $("#checkAvailability").click(function () {
      let userStartDate = new Date($("#startDate").val());
      let userEndDate = new Date($("#endDate").val());
      // check if the user entered valid dates
      if (isNaN(userStartDate) || isNaN(userEndDate)) {
        alert("Please enter valid start and end dates.");
        return;
      }

      // check if the start date is before the end date
      if (userStartDate >= userEndDate) {
        alert("The start date must be before the end date.");
        return;
      }

      // check if the start date is in the future
      if (userStartDate < new Date()) {
        alert("The start date must be in the future.");
        return;
      }

      // counter to keep track of the number of overlapping reservations
      let overlapCount = 0;

      // check if the user's dates overlap with any of the reservations
      reservations.forEach((reservation) => {
        if (
          userStartDate <= reservation.endDate &&
          userEndDate >= reservation.startDate
        ) {
          overlapCount++;
        }
      });

      // Display results
      if (overlapCount >= 10) {
        alert("There are no available spaces.");
      } else {
        alert(`There are ${10 - overlapCount} available spaces.`);
      }
    });
  });
});
