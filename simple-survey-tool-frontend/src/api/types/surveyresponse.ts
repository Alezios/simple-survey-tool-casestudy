import type { SurveyQuestion } from "./survey";

export type SurveyResponses = {
  surveyId: string;
  questions: SurveyQuestion[];
  answers: SurveyResponse[];
}

export type SurveyResponse = {
    answeredAt: Date;
    questionResponses: Map<string, any>;
}
