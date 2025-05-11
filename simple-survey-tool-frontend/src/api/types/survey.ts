export type Survey = {
  id: string;
  title: string;
  description: string;
  questions: SurveyQuestion[];
}

export type SurveyQuestion = {
    name: string;
    title: string;
    type: string;
    isRequired: boolean;
    // [...]  other properties specific to the question type are omitted for brevity, for this project full trust is placed in the backend to send the correct data 
}