import axios from 'axios';
import { API_CONFIG } from './config';
import type { Survey } from './types/survey';
import type { SurveyResponses } from './types/surveyresponse';

const apiClient = axios.create({
  baseURL: API_CONFIG.BASE_URL,
  headers: API_CONFIG.HEADERS,
});

export const surveyApi = {
  getAllSurveys: async (): Promise<Survey[]> => {
    const response = await apiClient.get(`/surveys`);
    return response.data;
  },

  getSurveyById: async (id: string): Promise<Survey> => {
    const response = await apiClient.get(`/surveys/${id}`);
    return response.data;
  },

  submitSurveyResponse: async (surveyId: string, answers: Record<string, any>): Promise<void> => {
    await apiClient.post(`/surveys/${surveyId}/response`, {
      surveyId,
      answers
    });
  },

  getSurveyResponses: async (surveyId: string): Promise<SurveyResponses> => {
    const response = await apiClient.get(`/surveys/${surveyId}/responses`);
    return response.data;
  }
};