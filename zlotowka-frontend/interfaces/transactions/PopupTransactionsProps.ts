import {OneTimeTransaction, TransactionData} from "@/interfaces/transactions/TransactionsData";

export interface AddTransactionProps {
  setShowAddTransaction: React.Dispatch<React.SetStateAction<boolean>>;
}

export interface EditTransactionProps {
  transaction: OneTimeTransaction;
  setShowEditTransaction: React.Dispatch<React.SetStateAction<boolean>>;
}

export interface TransactionFormProps {
  transaction?: TransactionData;
  onSubmitAction: (data: TransactionData) => void;
  onCloseAction: () => void;
  header: string;
  submitButtonText: string;
  submitButtonIcon: string;
}
