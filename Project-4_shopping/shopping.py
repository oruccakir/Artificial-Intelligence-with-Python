import csv
import sys

from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
import pandas as pd
import numpy as np

TEST_SIZE = 0.4


def main():

    # Load data from spreadsheet and split into train and test sets
    evidence, labels = load_data("shopping.csv")

    X_train, X_test, y_train, y_test = train_test_split(
        evidence, labels, test_size=TEST_SIZE
    )

    

    # Train model and make predictions
    model = train_model(X_train, y_train)
    predictions = model.predict(X_test)
    sensitivity, specificity = evaluate(y_test, predictions)

    # Print results
    print(f"Correct: {(y_test == predictions).sum()}")
    print(f"Incorrect: {(y_test != predictions).sum()}")
    print(f"True Positive Rate: {100 * sensitivity:.2f}%")
    print(f"True Negative Rate: {100 * specificity:.2f}%")


def load_data(filename):
    """
    Load shopping data from a CSV file `filename` and convert into a list of
    evidence lists and a list of labels. Return a tuple (evidence, labels).

    evidence should be a list of lists, where each list contains the
    following values, in order:
        - Administrative, an integer
        - Administrative_Duration, a floating point number
        - Informational, an integer
        - Informational_Duration, a floating point number
        - ProductRelated, an integer
        - ProductRelated_Duration, a floating point number
        - BounceRates, a floating point number
        - ExitRates, a floating point number
        - PageValues, a floating point number
        - SpecialDay, a floating point number
        - Month, an index from 0 (January) to 11 (December)
        - OperatingSystems, an integer
        - Browser, an integer
        - Region, an integer
        - TrafficType, an integer
        - VisitorType, an integer 0 (not returning) or 1 (returning)
        - Weekend, an integer 0 (if false) or 1 (if true)

    labels should be the corresponding list of labels, where each label
    is 1 if Revenue is true, and 0 otherwise.
    """

    shopping = pd.read_csv(filename)

    months = []

    visitors = []

    weekends = []

    shopping_weekend = shopping["Weekend"]

    shopping_month = shopping["Month"]

    shopping_visitor = shopping["VisitorType"]

    for weekend in shopping_weekend:
        if(weekend):
            weekends.append(1)
        else:
            weekends.append(0)

    shopping["Weekend"] = np.array(weekends).reshape(-1,1)

    for visitor in shopping_visitor:
        if(visitor == "Returning_Visitor"):
            visitors.append(1)
        else:
            visitors.append(0)
    
    shopping["VisitorType"] = np.array(visitors).reshape(-1,1)

    for month in shopping_month:
        if(month == "Jan"):
            months.append(0)
        elif(month == "Feb"):
            months.append(1)
        elif(month == "Mar"):
            months.append(2)
        elif(month == "Apr"):
            months.append(3)
        elif(month == "May"):
            months.append(4)
        elif(month == "Jun"):
            months.append(5)
        elif(month == "Jul"):
            months.append(6)
        elif(month == "Aug"):
            months.append(7)
        elif(month == "Sep"):
            months.append(8)
        elif(month == "Oct"):
            months.append(9)
        elif(month == "Nov"):
            months.append(10)
        elif(month == "Dec"):
            months.append(11)
        else:
            months.append(0)

    shopping["Month"] = np.array(months).reshape(-1,1)

    evidence = []

    labels = []

    for i in range(1230):
        temp_list = shopping.loc[i].values.tolist()
        labels.append(temp_list[17])
        temp_list = temp_list[:17]
        evidence.append(temp_list)
        



    return (evidence,labels)

    

    raise NotImplementedError


def train_model(evidence, labels):
    """
    Given a list of evidence lists and a list of labels, return a
    fitted k-nearest neighbor model (k=1) trained on the data.
    """
    model = KNeighborsClassifier(1)

    model.fit(evidence,labels)

    return model
    

    raise NotImplementedError


def evaluate(labels, predictions):
    """
    Given a list of actual labels and a list of predicted labels,
    return a tuple (sensitivity, specificity).

    Assume each label is either a 1 (positive) or 0 (negative).

    `sensitivity` should be a floating-point value from 0 to 1
    representing the "true positive rate": the proportion of
    actual positive labels that were accurately identified.

    `specificity` should be a floating-point value from 0 to 1
    representing the "true negative rate": the proportion of
    actual negative labels that were accurately identified.
    """
    estimated_positive = 0
    estimated_negative = 0
    
    real_positive = 0
    real_negative = 0

    for i in range(len(labels)):
        if(labels[i] == 1):
            real_positive = real_positive +1
        else:
            real_negative = real_negative +1
        
        if(predictions[i] == 1):
            estimated_positive = estimated_positive +1
        else:
            estimated_negative = estimated_negative +1

    sensivity = real_positive / (real_positive + estimated_negative)

    specifity = real_negative / (real_negative + estimated_positive)

    return (sensivity,specifity)



    raise NotImplementedError


if __name__ == "__main__":
    main()
